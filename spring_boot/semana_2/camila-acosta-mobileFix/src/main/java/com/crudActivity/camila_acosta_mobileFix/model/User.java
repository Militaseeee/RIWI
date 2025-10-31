package com.crudActivity.camila_acosta_mobileFix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // It's useful for specifying the table's name in the DB
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
