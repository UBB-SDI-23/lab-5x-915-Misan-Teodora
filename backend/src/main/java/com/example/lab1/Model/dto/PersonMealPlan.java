package com.example.lab1.Model.dto;

import com.example.lab1.Model.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;


@Data
@Builder
@AllArgsConstructor
public class PersonMealPlan {
    private Integer id;
    private String name;
    private Page<Meal> meals;
}
