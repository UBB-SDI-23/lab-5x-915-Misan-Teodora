package com.example.lab1.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "meal_plan", indexes = {@Index(name = "idx_person_id", columnList = "person_id")})
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "meal_id")
    private Integer meal_id;

    @Column(name= "time_of_creation", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime time_of_creation;

    @Column(name = "name")
    private String name;             // ex: "breakfast", "lunch", "dinner"
}