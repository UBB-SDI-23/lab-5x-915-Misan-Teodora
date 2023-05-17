package com.example.lab1.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name= "meal", indexes = {@Index(name = "idx_person_id", columnList = "person_id")})
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name="name")
    private String name;       // name of the meal

    @Column(name="type")
    private String type;       // ex: "breakfast", "lunch", "dinner"

    @Column(name="calories")
    private Integer calories;

    @Column(name="date")
    private String date;       // the date the meal was consumed

    @Column(name="notes")
    private String notes;      // additional notes or comments about the meal
}

