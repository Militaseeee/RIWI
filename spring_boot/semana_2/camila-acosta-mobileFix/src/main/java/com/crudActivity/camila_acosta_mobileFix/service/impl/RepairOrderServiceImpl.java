package com.crudActivity.camila_acosta_mobileFix.service.impl;

import com.crudActivity.camila_acosta_mobileFix.exception.BadRequestException;
import com.crudActivity.camila_acosta_mobileFix.exception.ConflictException;
import com.crudActivity.camila_acosta_mobileFix.exception.ForbiddenAccessException;
import com.crudActivity.camila_acosta_mobileFix.exception.ResourceNotFoundException;
import com.crudActivity.camila_acosta_mobileFix.model.*;
import com.crudActivity.camila_acosta_mobileFix.model.Role;
import com.crudActivity.camila_acosta_mobileFix.model.Status;
import com.crudActivity.camila_acosta_mobileFix.repository.DeviceRepository;
import com.crudActivity.camila_acosta_mobileFix.repository.RepairOrderRepository;
import com.crudActivity.camila_acosta_mobileFix.repository.UserRepository;
import com.crudActivity.camila_acosta_mobileFix.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service // Le dice a Spring que esta es la implementación
@Transactional // Todos los métodos públicos manejarán transacciones
public class RepairOrderServiceImpl implements RepairOrderService {

    @Autowired
    private RepairOrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    // --- Método para buscar un usuario por username (helper) ---
    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + username));
    }

    @Override
    public List<RepairOrder> findOrders(String username, Role role) {
        switch (role) {
            case ADMIN:
                return orderRepository.findAll();
            case TECH:
                User tech = getUserByUsername(username);
                return orderRepository.findByAssignedTech(tech);
            case USER:
                User customer = getUserByUsername(username);
                return orderRepository.findByCustomer(customer);
            default:
                throw new ForbiddenAccessException("Rol no reconocido");
        }
    }

    @Override
    public RepairOrder findOrderById(Long orderId, String username, Role role) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: " + orderId));

        switch (role) {
            case ADMIN:
                return order; // Admin puede ver todo
            case TECH:
                if (order.getAssignedTech() == null || !order.getAssignedTech().getUsername().equals(username)) {
                    throw new ForbiddenAccessException("No tienes permiso para ver esta orden");
                }
                return order;
            case USER:
                if (!order.getCustomer().getUsername().equals(username)) {
                    throw new ForbiddenAccessException("No tienes permiso para ver esta orden");
                }
                return order;
            default:
                throw new ForbiddenAccessException("Rol no reconocido");
        }
    }

    @Override
    public RepairOrder createOrder(Long deviceId, String issueDescription, String customerUsername) {
        User customer = getUserByUsername(customerUsername);
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo no encontrado: " + deviceId));

        RepairOrder order = new RepairOrder();
        order.setCustomer(customer);
        order.setDevice(device);
        order.setIssueDescription(issueDescription);
        order.setStatus(Status.PENDING); // Estado inicial

        return orderRepository.save(order);
    }

    @Override
    public RepairOrder assignTech(Long orderId, Long techId) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: " + orderId));

        User tech = userRepository.findById(techId)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado: " + techId));

        if (tech.getRole() != Role.TECH) {
            throw new BadRequestException("El usuario " + tech.getUsername() + " no es un técnico");
        }

        order.setAssignedTech(tech);
        return orderRepository.save(order);
    }

    @Override
    public RepairOrder changeStatus(Long orderId, Status newStatus, String techNotes, String techUsername, Role role) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: " + orderId));

        // Regla: Solo el técnico asignado (o un ADMIN) puede cambiar el estado
        if (role == Role.TECH) {
            if (order.getAssignedTech() == null || !order.getAssignedTech().getUsername().equals(techUsername)) {
                throw new ForbiddenAccessException("No estás asignado a esta orden");
            }
        }

        // Regla: No se puede cambiar a PENDING o CANCELED desde aquí (deleteOrder es para CANCELED)
        if (newStatus == Status.PENDING || newStatus == Status.CANCELED) {
            throw new BadRequestException("No se puede cambiar el estado a " + newStatus);
        }

        // Regla de flujo: (PENDING -> IN_PROGRESS -> READY -> DELIVERED)
        if (order.getStatus() == Status.PENDING && newStatus != Status.IN_PROGRESS) {
            throw new ConflictException("Una orden PENDIENTE solo puede pasar a EN PROGRESO");
        }
        if (order.getStatus() == Status.IN_PROGRESS && newStatus != Status.READY) {
            throw new ConflictException("Una orden EN PROGRESO solo puede pasar a LISTA");
        }
        if (order.getStatus() == Status.READY && newStatus != Status.DELIVERED) {
            throw new ConflictException("Una orden LISTA solo puede pasar a ENTREGADA");
        }

        order.setStatus(newStatus);
        if (techNotes != null && !techNotes.isBlank()) {
            order.setTechNotes(order.getTechNotes() + "\n" + techNotes); // Añade notas
        }

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId, String username, Role role) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: " + orderId));

        if (role == Role.ADMIN) {
            // Un admin puede borrarla (o cancelarla, es tu decisión)
            // orderRepository.delete(order);
            // Es mejor práctica cambiar el estado a CANCELED
            order.setStatus(Status.CANCELED);
            orderRepository.save(order);
            return;
        }

        if (role == Role.USER) {
            if (!order.getCustomer().getUsername().equals(username)) {
                throw new ForbiddenAccessException("No eres el dueño de esta orden");
            }
            if (order.getStatus() != Status.PENDING) {
                throw new ConflictException("Solo puedes cancelar órdenes que están PENDIENTES");
            }

            order.setStatus(Status.CANCELED); // Cambiamos estado en vez de borrar
            orderRepository.save(order);
        } else {
            throw new ForbiddenAccessException("Los técnicos no pueden cancelar órdenes");
        }
    }
}