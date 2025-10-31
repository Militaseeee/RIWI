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

    // Helper para buscar usuario por ID
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<RepairOrder> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public RepairOrder findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));
    }

    // Metodo para crear orden -> recibe customerId
    @Override
    public RepairOrder createOrder(Long deviceId, String issueDescription, Long customerId) {
        User customer = getUserById(customerId); // Usa el nuevo helper
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found: " + deviceId));

        // ... (resto del código igual) ...
        RepairOrder order = new RepairOrder();
        order.setCustomer(customer);
        order.setDevice(device);
        order.setIssueDescription(issueDescription);
        order.setStatus(Status.PENDING);

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

    // cambiar estado: recibe el ID del usuario (técnico/admin) que hace la acción
    @Override
    public RepairOrder changeStatus(Long orderId, Status newStatus, String techNotes, Long actorId) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        User actor = getUserById(actorId);

        // 1. VALIDACIÓN DE PERMISOS (Quién puede hacer el cambio)
        if (actor.getRole() == Role.TECH) {
            // Si es un TÉCNICO, debe ser el técnico asignado
            if (order.getAssignedTech() == null || !Objects.equals(order.getAssignedTech().getId(), actorId)) {
                // Se usa Objects.equals para comparar el Long (actorId) con el ID del técnico
                throw new ForbiddenAccessException("You are not assigned to this order");
            }
        } else if (actor.getRole() != Role.ADMIN) {
            // Si no es TECH ni ADMIN, no puede cambiar el estado
            throw new ForbiddenAccessException("Only assigned technicians or Admins can change the status.");
        }

        // 2. VALIDACIÓN DEL ESTADO INICIAL Y FINAL (Reglas de Flujo)

        // Los estados PENDING y CANCELED no se establecen con este método
        if (newStatus == Status.PENDING || newStatus == Status.CANCELED) {
            throw new BadRequestException("The state cannot be changed to " + newStatus);
        }

        // Se verifica la secuencia de flujo: PENDING -> IN_PROGRESS -> READY -> DELIVERED
        Status currentStatus = order.getStatus();

        if (currentStatus == Status.PENDING && newStatus != Status.IN_PROGRESS) {
            throw new ConflictException("A PENDING order can only be moved to IN_PROGRESS");
        }

        if (currentStatus == Status.IN_PROGRESS && newStatus != Status.READY) {
            throw new ConflictException("An order IN_PROGRESS can only be moved to READY");
        }

        if (currentStatus == Status.READY && newStatus != Status.DELIVERED) {
            throw new ConflictException("A READY order can only be moved to DELIVERED");
        }

        // Si la orden ya fue DELIVERED o CANCELED, no puede ser modificada.
        if (currentStatus == Status.DELIVERED || currentStatus == Status.CANCELED) {
            throw new ConflictException("Cannot change the status of a " + currentStatus + " order.");
        }

        // 3. APLICACIÓN DEL CAMBIO
        order.setStatus(newStatus);

        if (techNotes != null && !techNotes.isBlank()) {
            // Agrega las nuevas notas a las notas existentes
            String existingNotes = order.getTechNotes() != null ? order.getTechNotes() : "";
            order.setTechNotes(existingNotes + "\n" + techNotes);
        }

        return orderRepository.save(order);
    }

    // Elimina una orden: recibe el ID del usuario (cliente/admin) que hace la acción
    @Override
    public void deleteOrder(Long orderId, Long actorId) {
        RepairOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        User actor = getUserById(actorId);

        if (actor.getRole() == Role.ADMIN) {
            // Un admin puede 'borrarla' (cancelarla)
            order.setStatus(Status.CANCELED);
            orderRepository.save(order);
            return;
        }

        if (actor.getRole() == Role.USER) {
            if (!Objects.equals(order.getCustomer().getId(), actorId)) {
                throw new ForbiddenAccessException("You are not the owner of this order");
            }

            if (order.getStatus() != Status.PENDING) {
                throw new ConflictException("You can only cancel orders that are PENDING");
            }

            order.setStatus(Status.CANCELED);
            orderRepository.save(order);
        } else {
            // TECH no puede cancelar
            throw new ForbiddenAccessException("Technicians cannot cancel orders");
        }
    }

}