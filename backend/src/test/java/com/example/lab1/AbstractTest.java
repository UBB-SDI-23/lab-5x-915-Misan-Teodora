package com.example.lab1;

import com.example.lab1.Model.Meal;
import com.example.lab1.Model.MealPlan;
import com.example.lab1.Repository.Repo;
import com.example.lab1.Repository.RepoMealPlan;
import com.example.lab1.Repository.RepoPers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.lab1.Model.Person;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@ActiveProfiles("test")
@Testcontainers
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private Repo mealRepository;
    @Autowired
    private RepoPers personRepository;
    @Autowired
    private RepoMealPlan mealPlanRepository;

    @BeforeAll
    public void initTestData() {

        // setting up person data
        Person person1 = getDefaultUser(1);
        Person person2 = getDefaultUser(2);

        personRepository.save(person1);
        personRepository.save(person2);

        // setting up meal data

        Meal meal1 = getTestMeal(1, person1.getId(), 1);
        Meal meal2 = getTestMeal(2, person1.getId(), 5);
        Meal meal3 = getTestMeal(3, person2.getId(), 3);

        mealRepository.save(meal1);
        mealRepository.save(meal2);
        mealRepository.save(meal3);

        // setting up meal plans

        MealPlan mealPlan1 = getMealPlan(1, person1.getId(), meal1.getId());
        MealPlan mealPlan2 = getMealPlan(2, person1.getId(), meal3.getId());
        MealPlan mealPlan3 = getMealPlan(3, person2.getId(), meal1.getId());
        MealPlan mealPlan4 = getMealPlan(4, person2.getId(), meal2.getId());
        MealPlan mealPlan5 = getMealPlan(5, person2.getId(), meal3.getId());

        mealPlanRepository.save(mealPlan1);
        mealPlanRepository.save(mealPlan2);
        mealPlanRepository.save(mealPlan3);
        mealPlanRepository.save(mealPlan4);
        mealPlanRepository.save(mealPlan5);
    }

    protected static Person getDefaultUser(Integer i) {
        Person person = new Person();
        person.setName("Test user" + i);
        person.setAge(22);
        person.setKg(55);
        person.setHeight(172);

        return person;
    }

    protected static Meal getTestMeal(Integer i, Integer personId, Integer calories) {
        Meal meal = new Meal();
        meal.setPersonId(personId);
        meal.setCalories(calories);
        meal.setDate("2023-01-01");
        meal.setName("test-meal" + i);
        meal.setType("breakfast");
        meal.setNotes("");

        return meal;
    }

    protected static MealPlan getMealPlan(Integer i, Integer personId, Integer mealId){
        MealPlan mealPlan = new MealPlan();
        mealPlan.setPerson_id(personId);
        mealPlan.setMeal_id(mealId);
        mealPlan.setName("meal plan " + i);
        mealPlan.setTime_of_creation(LocalDateTime.now());

        return mealPlan;
    }
}
