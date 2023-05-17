package com.example.lab1.Controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.lab1.Model.Meal;
import com.example.lab1.Service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.lab1.Model.MealPlan;
import com.example.lab1.Model.Person;
import com.example.lab1.Model.dto.AverageCalories;
import com.example.lab1.Model.dto.PersonMeals;
import com.example.lab1.Service.PlanService;

@RestController
@RequestMapping(path = "/api/plan", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerMealPlan {
    
    @Autowired
    private PlanService planService;

    @Autowired
    private NutritionService mealService;

    @GetMapping("/status")
    public String status() {
        return "running";
    }

    @GetMapping("")
    public List<MealPlan> getMeals() {
        return planService.getAllPlans();
    }

    @GetMapping("/filter")
    public List<PersonMeals> filterPersons() {
        return planService.statisticalFilter();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonMeals> getMealPlansPerPerson(@PathVariable Integer id) {
        return planService.getMealPlansByUserId(id);
    }


    @PostMapping(value = "/{id}/meals")
    public void setParticipants(@PathVariable Integer id, @RequestBody List<Meal> meals) {
        meals.forEach(meal ->
                        planService.add(MealPlan.builder()
                                        .person_id(id)
                                        .meal_id(meal.getId())
                                        .time_of_creation(LocalDateTime.now())
                                        .name("")
                                .build()));
    }

    @GetMapping("/average")
    public List<AverageCalories> averageCal() {
        return planService.getStatisticalCalories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getPlan(@PathVariable Integer id) {
        return planService.getByID(id);
    }

    @PostMapping
    public ResponseEntity<MealPlan> add(@RequestBody MealPlan plan) {
        return planService.add(plan);
    }

    @PutMapping
    public ResponseEntity<MealPlan> update(@RequestBody MealPlan plan) {
        return planService.update(plan);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        planService.delete(id);
    }
}
