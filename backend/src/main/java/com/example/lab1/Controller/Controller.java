package com.example.lab1.Controller;


import com.example.lab1.Model.Meal;
import com.example.lab1.Model.dto.MealWithPerson;
import com.example.lab1.Service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/meal", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @Autowired
    private NutritionService nutritionService;

    @GetMapping("/status")
    public String status() {
        return "running";
    }

    @GetMapping("")
    public Page<Meal> getMeals(@PageableDefault Pageable pageable) {
        return nutritionService.getAllMeals(pageable);
    }

    @GetMapping("/{id}")
    public MealWithPerson getMealByID(@PathVariable Integer id) {
        return nutritionService.getByID(id);
    }

    @GetMapping("/filter/{calories}")
    public Page<Meal> filteringByCalories(@PathVariable Integer calories, @PageableDefault Pageable pageable) {
        return nutritionService.filter(calories, pageable);
    }

    @PostMapping
    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal) {

        return nutritionService.add(meal);
    }

    @PutMapping
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal) {
        return nutritionService.update(meal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable Integer id) {
        nutritionService.delete(id);
    }
}
