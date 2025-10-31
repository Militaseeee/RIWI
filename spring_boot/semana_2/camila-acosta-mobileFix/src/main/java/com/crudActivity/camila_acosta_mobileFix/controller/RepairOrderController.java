package com.crudActivity.camila_acosta_mobileFix.controller;

import com.crudActivity.camila_acosta_mobileFix.dto.ChangeStatusRequest;
import com.crudActivity.camila_acosta_mobileFix.dto.CreateOrderRequest;
import com.crudActivity.camila_acosta_mobileFix.model.RepairOrder;
import com.crudActivity.camila_acosta_mobileFix.model.Role;
import com.crudActivity.camila_acosta_mobileFix.service.RepairOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders") // Ruta base para todas las peticiones de este controlador
public class RepairOrderController {

    // Inyectamos la interfaz
    @Autowired
    private RepairOrderService repairOrderService;

    // GET /api/orders (Devuelve todas, el front-end tendrá que filtrar por ID de usuario)
    @GetMapping
    public ResponseEntity<List<RepairOrder>> getOrders() {
        // Ahora el servicio devuelve TODAS las órdenes sin filtro de rol
        List<RepairOrder> orders = repairOrderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    // GET /api/orders/{id}
    // Si necesitas verificar permisos en el front-end, también necesitarías un actorId.
    @GetMapping("/{id}")
    public ResponseEntity<RepairOrder> getOrderById(@PathVariable Long id) {
        // findOrderById ya no necesita username y role
        RepairOrder order = repairOrderService.findOrderById(id);
        return ResponseEntity.ok(order);
    }

    // POST /api/orders/{customerId} -> Crea una nueva orden. Ahora necesita el ID del cliente.
    @PostMapping("/{customerId}")
    public ResponseEntity<RepairOrder> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            @PathVariable Long customerId) { // Recibe el ID del cliente que hace la solicitud

        RepairOrder newOrder = repairOrderService.createOrder(
                request.deviceId(),
                request.issueDescription(),
                customerId // Pasa el ID al servicio
        );

        // Devuelve 201 Created
        URI location = URI.create("/api/orders/" + newOrder.getId());
        return ResponseEntity.created(location).body(newOrder);
    }

    // PUT /api/orders/{id}/assign/{techId} (admin) -> Asigna un técnico (NO CAMBIA)
    @PutMapping("/{id}/assign/{techId}")
    public ResponseEntity<RepairOrder> assignTech(
            @PathVariable Long id,
            @PathVariable Long techId) {

        RepairOrder updatedOrder = repairOrderService.assignTech(id, techId);
        return ResponseEntity.ok(updatedOrder);
    }

    // PUT /api/orders/{id}/status/{actorId} (tech, admin) -> Cambia el estado. Necesita el ID de quien actúa.
    @PutMapping("/{id}/status/{actorId}")
    public ResponseEntity<RepairOrder> changeStatus(
            @PathVariable Long id,
            @PathVariable Long actorId, // El ID del técnico o admin que hace el cambio
            @Valid @RequestBody ChangeStatusRequest request) {

        // Ahora el servicio maneja la validación de roles y permisos internamente usando el actorId
        RepairOrder updatedOrder = repairOrderService.changeStatus(
                id,
                request.status(),
                request.techNotes(),
                actorId // Pasa el ID del usuario que está actuando
        );
        return ResponseEntity.ok(updatedOrder);
    }

    // DELETE /api/orders/{id}/cancel/{actorId} (user -> si es pending --- admin)
    @DeleteMapping("/{id}/cancel/{actorId}") // La nueva ruta incluye quién cancela
    public ResponseEntity<Void> cancelOrder(
            @PathVariable Long id,
            @PathVariable Long actorId) { // El ID del usuario (cliente o admin) que hace la acción

        repairOrderService.deleteOrder(id, actorId); // deleteOrder ahora usa el actorId

        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}