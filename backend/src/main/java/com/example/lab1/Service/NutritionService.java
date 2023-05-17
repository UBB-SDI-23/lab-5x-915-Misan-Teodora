package com.example.lab1.Service;

import com.example.lab1.Model.Meal;
import com.example.lab1.Model.Person;
import com.example.lab1.Model.dto.MealWithPerson;
import com.example.lab1.Repository.Repo;
import com.example.lab1.Repository.RepoPers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutritionService {
    private final Repo nutritionRepo;
    private final RepoPers personRepo;

    public List<Meal> getAllMeals() {
        return StreamSupport.stream(nutritionRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public MealWithPerson getByID(Integer id) {
        log.info("Get by id: {} invoked.", id);
        Meal meal =  nutritionRepo.findById(id).orElseThrow();
        Person person = personRepo.findById(meal.getPersonId()).orElseThrow();
        return new MealWithPerson(meal.getId(), meal.getName(), meal.getType(), meal.getCalories(), meal.getNotes() ,person);
    }

    public List<Meal> filter(Integer calories) {
        return StreamSupport.stream(nutritionRepo.filterByCalories(calories.toString()).spliterator(), false)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Meal> add(Meal meal) {
        log.info("POST method invoked.");
        return new ResponseEntity<>(nutritionRepo.save(meal), HttpStatus.CREATED);
    }

    public ResponseEntity<Meal> update(Meal meal) {
        log.info("PUT method invoked.");
        Optional<Meal> existent =  nutritionRepo.findById(meal.getId());
        if(existent.isEmpty()) {
            log.info("Unable to update meal with id: {}. It was not found.", meal.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nutritionRepo.save(meal), HttpStatus.OK);
    }

    public void delete(Integer id) {
        log.info("DELETE method invoked.");
        nutritionRepo.deleteById(id);
    }
}
