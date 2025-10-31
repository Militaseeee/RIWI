package com.crudActivity.camila_acosta_mobileFix.service;

import com.crudActivity.camila_acosta_mobileFix.model.RepairOrder;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import com.crudActivity.camila_acosta_mobileFix.model.Role;
import com.crudActivity.camila_acosta_mobileFix.model.Status;
import java.util.List;

public interface RepairOrderService {

    // Busca ordenes según el rol del usuario
    // ADMIN: todas --- TECH: asignadas --- USER: propias
    List<RepairOrder> findOrders(String username, Role role);

    // Busca una orden por ID, verificando permisos
    RepairOrder findOrderById(Long orderId, String username, Role role);

    // USER -> crea una nueva orden
    RepairOrder createOrder(Long deviceId, String issueDescription, String customerUsername);

    // ADMIN -> asigna un tEcnico a una orden
    RepairOrder assignTech(Long orderId, Long techId);

    // TECH -> actualiza el estado de una orden
    RepairOrder changeStatus(Long orderId, Status newStatus, String techNotes, String techUsername, Role role);

    // USER -> cancela su orden (si PENDING)
    // ADMIN -> borra cualquier orden
    void deleteOrder(Long orderId, String username, Role role);
}