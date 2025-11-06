package com.mobilefix_v2.camila_acosta_mobilefix_v2.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // Marca la clase como entidad JPA
@Table(name = "repair_orders")
@EntityListeners(AuditingEntityListener.class) // Activa auditoría (creación/modificación automática)
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementa el ID
    private Long id;

    // Relación -> Muchas órdenes pertenecen a un cliente (User)
    @ManyToOne(fetch = FetchType.LAZY) // Muchas órdenes -> un cliente (relación)
    @JoinColumn(name = "customer_id", nullable = false) // Nombre de la FK en la tabla
    private User customer;

    // Relación -> Muchas órdenes son para un dispositivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false) // Campo obligatorio
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    // Relación -> Muchas órdenes pueden ser asignadas a UN técnico (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_id") // Es nullable, puede no estar asignado
    private User assignedTech;

    @Column(columnDefinition = "TEXT") // Para notas más largas
    private String techNotes;

    // --- Campos de Auditoría ---
    @CreatedDate // Fecha de creación (se llena sola)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Fecha de última modificación (se actualiza sola)
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getAssignedTech() {
        return assignedTech;
    }

    public void setAssignedTech(User assignedTech) {
        this.assignedTech = assignedTech;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public void setTechNotes(String techNotes) {
        this.techNotes = techNotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
