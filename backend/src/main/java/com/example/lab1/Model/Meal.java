package com.example.lab1.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Table(name= "meal")
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

