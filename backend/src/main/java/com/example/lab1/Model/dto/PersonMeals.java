package com.example.lab1.Model.dto;

import java.util.List;

import com.example.lab1.Model.Meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonMeals {
    private Integer id;
    private String name;
    private List<Meal> meals;
}
