package com.example.lab1.Model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AverageCalories {
    private Integer personID;
    private String personName;
    private List<String> mealNames;
    private Double average;
}
