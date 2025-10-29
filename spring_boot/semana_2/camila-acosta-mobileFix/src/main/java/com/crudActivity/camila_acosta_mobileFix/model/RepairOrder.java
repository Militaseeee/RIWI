package com.crudActivity.camila_acosta_mobileFix.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
// Activates an event listener that allows Spring to automatically fill in the fields:
@EntityListeners(AuditingEntityListener.class) // For @CreatedDate y @LastModifiedDate
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship with another entity
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false)
    private String issueDescription;

    // Save enums as text or number
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "tech_id") // Nulable
    private User assignedTech;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // It allows you to save long text or binary files, for example, extensive technician observations
    @Lob // Lob stands for "Large Object"
    private String techNotes;

}
