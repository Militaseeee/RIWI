package com.crudActivity.camila_acosta_mobileFix.controller;

import com.crudActivity.camila_acosta_mobileFix.dto.ChangeStatusRequest;
import com.crudActivity.camila_acosta_mobileFix.dto.CreateOrderRequest;
import com.crudActivity.camila_acosta_mobileFix.model.RepairOrder;
import com.crudActivity.camila_acosta_mobileFix.model.Role;
import com.crudActivity.camila_acosta_mobileFix.service.RepairOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders") // Ruta base para todas las peticiones de este controlador
public class RepairOrderController {

    // Inyectamos la INTERFAZ, no la implementación
    @Autowired
    private RepairOrderService repairOrderService;

    // --- Helper para obtener el Rol del usuario autenticado ---
    private Role getRoleFromAuthentication(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .map(roleName -> roleName.replace("ROLE_", "")) // Quita el prefijo "ROLE_"
                .map(Role::valueOf) // Convierte String a enum Role
                .orElseThrow(() -> new IllegalStateException("El usuario no tiene un rol definido."));
    }

    /**
     * Endpoint: GET /api/orders
     * Permisos: USER, TECH, ADMIN
     * Devuelve órdenes según el rol.
     */
    @GetMapping
    public ResponseEntity<List<RepairOrder>> getOrders(Authentication authentication) {
        String username = authentication.getName();
        Role role = getRoleFromAuthentication(authentication);

        List<RepairOrder> orders = repairOrderService.findOrders(username, role);
        return ResponseEntity.ok(orders);
    }

    /**
     * Endpoint: GET /api/orders/{id}
     * Permisos: USER (si es suya), TECH (si está asignado), ADMIN
     */
    @GetMapping("/{id}")
    public ResponseEntity<RepairOrder> getOrderById(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        Role role = getRoleFromAuthentication(authentication);

        RepairOrder order = repairOrderService.findOrderById(id, username, role);
        return ResponseEntity.ok(order); // findOrderById ya maneja el 404 y 403
    }

    /**
     * Endpoint: POST /api/orders
     * Permisos: USER
     * Crea una nueva orden de reparación.
     */
    @PostMapping
    public ResponseEntity<RepairOrder> createOrder(
            @Valid @RequestBody CreateOrderRequest request, // @Valid activa las validaciones del DTO
            Authentication authentication) {

        String username = authentication.getName(); // El username del USER logueado

        RepairOrder newOrder = repairOrderService.createOrder(
                request.deviceId(),
                request.issueDescription(),
                username
        );

        // Devuelve 201 Created
        URI location = URI.create("/api/orders/" + newOrder.getId());
        return ResponseEntity.created(location).body(newOrder);
    }

    /**
     * Endpoint: PUT /api/orders/{id}/assign/{techId}
     * Permisos: ADMIN
     * Asigna un técnico a una orden.
     */
    @PutMapping("/{id}/assign/{techId}")
    public ResponseEntity<RepairOrder> assignTech(
            @PathVariable Long id,
            @PathVariable Long techId) {

        RepairOrder updatedOrder = repairOrderService.assignTech(id, techId);
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * Endpoint: PUT /api/orders/{id}/status
     * Permisos: TECH, ADMIN
     * Cambia el estado de una orden.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<RepairOrder> changeStatus(
            @PathVariable Long id,
            @Valid @RequestBody ChangeStatusRequest request,
            Authentication authentication) {

        String username = authentication.getName();
        Role role = getRoleFromAuthentication(authentication);

        RepairOrder updatedOrder = repairOrderService.changeStatus(
                id,
                request.status(),
                request.techNotes(),
                username,
                role
        );
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * Endpoint: DELETE /api/orders/{id}
     * Permisos: USER (si es PENDING y suya), ADMIN
     * El servicio lo cambia a CANCELED.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(
            @PathVariable Long id,
            Authentication authentication) {

        String username = authentication.getName();
        Role role = getRoleFromAuthentication(authentication);

        repairOrderService.deleteOrder(id, username, role);

        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}