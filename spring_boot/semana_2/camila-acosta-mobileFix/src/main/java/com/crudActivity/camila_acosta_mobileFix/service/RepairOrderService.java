package com.crudActivity.camila_acosta_mobileFix.service;

import com.crudActivity.camila_acosta_mobileFix.model.RepairOrder;
import com.crudActivity.camila_acosta_mobileFix.model.User;
import com.crudActivity.camila_acosta_mobileFix.model.Role;
import com.crudActivity.camila_acosta_mobileFix.model.Status;
import java.util.List;

public interface RepairOrderService {

    /**
     * Busca órdenes según el rol del usuario.
     * ADMIN: todas. TECH: asignadas. USER: propias.
     */
    List<RepairOrder> findOrders(String username, Role role);

    /**
     * Busca una orden por ID, verificando permisos.
     */
    RepairOrder findOrderById(Long orderId, String username, Role role);

    /**
     * USER crea una nueva orden.
     * DTO: Necesitaremos un DTO (Data Transfer Object) para esto,
     * pero por ahora usemos los campos simples.
     */
    RepairOrder createOrder(Long deviceId, String issueDescription, String customerUsername);

    /**
     * ADMIN asigna un técnico a una orden.
     */
    RepairOrder assignTech(Long orderId, Long techId);

    /**
     * TECH (o ADMIN) actualiza el estado de una orden.
     */
    RepairOrder changeStatus(Long orderId, Status newStatus, String techNotes, String techUsername, Role role);

    /**
     * USER cancela su orden (si PENDING).
     * ADMIN cancela/borra cualquier orden.
     */
    void deleteOrder(Long orderId, String username, Role role);
}