package com.crudActivity.camila_acosta_mobileFix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user") // It's useful for specifying the table's name in the DB
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the ID
    private long id;

    // Configure the column details in the database
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Save enums as text or number
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String fullName;
    private String email;
    private boolean enabled = true;
}
