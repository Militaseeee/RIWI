package com.crudActivity.camila_acosta_mobileFix.service;

import com.crudActivity.camila_acosta_mobileFix.model.RepairOrder;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import com.crudActivity.camila_acosta_mobileFix.model.Role;
import com.crudActivity.camila_acosta_mobileFix.model.Status;
import java.util.List;

public interface RepairOrderService {

    // Busca ordenes según el rol del usuario
    // ADMIN: todas --- TECH: asignadas --- USER: propias
    List<RepairOrder> findAllOrders();

    // Busca una orden por ID, verificando permisos
    RepairOrder findOrderById(Long orderId);

    // USER -> crea una nueva orden -> necesita el ID del cliente, no el username
    RepairOrder createOrder(Long deviceId, String issueDescription, Long customerId);

    // ADMIN -> asigna un tEcnico a una orden
    RepairOrder assignTech(Long orderId, Long techId);

    // TECH -> actualiza el estado de una orden
    RepairOrder changeStatus(Long orderId, Status newStatus, String techNotes, Long actorId);

    // USER -> cancela su orden (si PENDING)
    // ADMIN -> borra cualquier orden
    void deleteOrder(Long orderId, Long actorId);
}