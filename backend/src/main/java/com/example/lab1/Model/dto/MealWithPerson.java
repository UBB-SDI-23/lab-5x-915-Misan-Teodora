package com.example.lab1.Model.dto;
import com.example.lab1.Model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MealWithPerson {
    private Integer mealID;
    private String mealName;
    private String type;
    private Integer calories;
    private String notes;
    private Person person;
}
