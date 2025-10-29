package com.crudActivity.camila_acosta_mobileFix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user") // It's useful for specifying the table's name in the DB
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
