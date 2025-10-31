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

    // Metodo para buscar un usuario por username (helper)
    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }

    // Mtodo para obtener ordenes según el rol
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
                throw new ForbiddenAccessException("Unrecognized role");
        }
    }

    // Buscar una orden por ID con validación de permisos
    @Override
    public RepairOrder findOrderById(Long orderId, String username, Role role) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        switch (role) {
            case ADMIN:
                return order; // Admin puede ver todos
            case TECH:
                if (order.getAssignedTech() == null || !order.getAssignedTech().getUsername().equals(username)) {
                    throw new ForbiddenAccessException("You do not have permission to view this order");
                }
                return order;
            case USER:
                if (!order.getCustomer().getUsername().equals(username)) {
                    throw new ForbiddenAccessException("You do not have permission to view this order");
                }
                return order;
            default:
                throw new ForbiddenAccessException("Unrecognized role");
        }
    }

    // Metodo para crear una nueva orden de reparación
    @Override
    public RepairOrder createOrder(Long deviceId, String issueDescription, String customerUsername) {
        User customer = getUserByUsername(customerUsername);
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found: " + deviceId));

        RepairOrder order = new RepairOrder();
        order.setCustomer(customer);
        order.setDevice(device);
        order.setIssueDescription(issueDescription);
        order.setStatus(Status.PENDING); // Estado inicial

        return orderRepository.save(order);
    }

    // Metodo para asignar técnico a una orden
    @Override
    public RepairOrder assignTech(Long orderId, Long techId) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        User tech = userRepository.findById(techId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found: " + techId));

        if (tech.getRole() != Role.TECH) {
            throw new BadRequestException("The user " + tech.getUsername() + " is not a technician");
        }

        order.setAssignedTech(tech);
        return orderRepository.save(order);
    }

    // Metodo para cambiar el estado de una orden
    @Override
    public RepairOrder changeStatus(Long orderId, Status newStatus, String techNotes, String techUsername, Role role) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        // Solo el técnico asignado o el admin puede cambiar el estado
        if (role == Role.TECH) {
            if (order.getAssignedTech() == null || !order.getAssignedTech().getUsername().equals(techUsername)) {
                throw new ForbiddenAccessException("You are not assigned to this order");
            }
        }

        // No se puede cambiar a pending o canceled desde aquí -> deleteOrder es para canceled
        if (newStatus == Status.PENDING || newStatus == Status.CANCELED) {
            throw new BadRequestException("The state cannot be changed to " + newStatus);
        }

        // Flujo: (PENDING -> IN_PROGRESS -> READY -> DELIVERED)
        if (order.getStatus() == Status.PENDING && newStatus != Status.IN_PROGRESS) {
            throw new ConflictException("A PENDING order can only be moved to IN PROGRESS");
        }
        if (order.getStatus() == Status.IN_PROGRESS && newStatus != Status.READY) {
            throw new ConflictException("An order IN PROGRESS can only be moved to LIST");
        }
        if (order.getStatus() == Status.READY && newStatus != Status.DELIVERED) {
            throw new ConflictException("A READY order can only be moved to DELIVERED");
        }

        order.setStatus(newStatus);
        if (techNotes != null && !techNotes.isBlank()) {
            order.setTechNotes(order.getTechNotes() + "\n" + techNotes); // Añade notas
        }

        return orderRepository.save(order);
    }

    // Elimina una orden
    @Override
    public void deleteOrder(Long orderId, String username, Role role) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        if (role == Role.ADMIN) {
            // Un admin puede borrarla
            order.setStatus(Status.CANCELED); // Lo coloco como cancelado
            orderRepository.save(order);
            return;
        }

        if (role == Role.USER) {
            if (!order.getCustomer().getUsername().equals(username)) {
                throw new ForbiddenAccessException("You are not the owner of this order");
            }
            if (order.getStatus() != Status.PENDING) {
                throw new ConflictException("You can only cancel orders that are PENDING");
            }

            order.setStatus(Status.CANCELED); // Cambiamos estado en vez de borrar
            orderRepository.save(order);
        } else {
            throw new ForbiddenAccessException("Technicians cannot cancel orders");
        }
    }
}