package com.example.springbootjasperreports.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public UUID id;
    public String email;
    public String password;
    public String name;
    public String role;

    public User(String email, String name, String number, String admin) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
