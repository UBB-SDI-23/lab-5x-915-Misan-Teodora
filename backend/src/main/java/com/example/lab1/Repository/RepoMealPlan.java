package com.example.lab1.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.lab1.Model.MealPlan;

public interface RepoMealPlan extends CrudRepository<MealPlan, Integer> {
    
}
