package com.example.lab1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "meal_plan", indexes = {@Index(name = "idx_person_id", columnList = "person_id")})
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "meal_id")
    private Integer meal_id;

    @Column(name = "time_of_creation", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime time_of_creation;

    @Column(name = "name")
    private String name;             // ex: "breakfast", "lunch", "dinner"

    public static MealPlanBuilder builder() {
        return new MealPlanBuilder();
    }

    public static class MealPlanBuilder {
        private Integer id;
        private Integer personId;
        private Integer meal_id;
        private LocalDateTime time_of_creation;
        private String name;

        MealPlanBuilder() {
        }

        public MealPlanBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public MealPlanBuilder personId(Integer personId) {
            this.personId = personId;
            return this;
        }

        public MealPlanBuilder meal_id(Integer meal_id) {
            this.meal_id = meal_id;
            return this;
        }

        public MealPlanBuilder time_of_creation(LocalDateTime time_of_creation) {
            this.time_of_creation = time_of_creation;
            return this;
        }

        public MealPlanBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MealPlan build() {
            return new MealPlan(this.id, this.personId, this.meal_id, this.time_of_creation, this.name);
        }

        public String toString() {
            return "MealPlan.MealPlanBuilder(id=" + this.id + ", personId=" + this.personId + ", meal_id=" + this.meal_id + ", time_of_creation=" + this.time_of_creation + ", name=" + this.name + ")";
        }
    }
}