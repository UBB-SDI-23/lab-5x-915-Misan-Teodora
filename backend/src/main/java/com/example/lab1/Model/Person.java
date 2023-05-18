package com.example.lab1.Model;


import com.example.lab1.security.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name= "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;   
    
    @Column(name="age")
    private Integer age;

    @Column(name="kg")
    private Integer kg;

    @Column(name="height")
    private Integer height;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private ERole role = ERole.ROLE_USER; // default value
}