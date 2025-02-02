package com.example.model;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id; 
    public String firstName;
    public String lastName;
    public String email;
    public String password;
}
