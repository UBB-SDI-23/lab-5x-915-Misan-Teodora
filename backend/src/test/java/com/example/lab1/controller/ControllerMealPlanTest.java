package com.example.lab1.controller;

import com.example.lab1.Model.dto.AverageCalories;
import com.example.lab1.Model.dto.PersonMeals;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.example.lab1.AbstractTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ControllerMealPlanTest extends AbstractTest {
    protected final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Disabled
    public void testStatisticalFilter() throws Exception {

        String result = mockMvc.perform(get("/api/plan/filter"))
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        List<PersonMeals> personMealsList = objectMapper.readValue(result, new TypeReference<List<PersonMeals>>() {});

        Assertions.assertNotNull(personMealsList);
        Assertions.assertEquals(2, personMealsList.size());

        PersonMeals personMeal1 = personMealsList.get(0);
        PersonMeals personMeal2 = personMealsList.get(1);

        Assertions.assertTrue(personMeal1.getMeals().size() > personMeal2.getMeals().size());

        Assertions.assertEquals(2 , personMeal1.getId());
        Assertions.assertEquals(3 , personMeal1.getMeals().size());

        Assertions.assertEquals(1 , personMeal2.getId());
        Assertions.assertEquals(2 , personMeal2.getMeals().size());
    }


    @Test
    @Disabled
    public void testAverage() throws Exception {

        String result = mockMvc.perform(get("/api/plan/average"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<AverageCalories> averageCalories = objectMapper.readValue(result, new TypeReference<List<AverageCalories>>() {});

        Assertions.assertNotNull(averageCalories);
        Assertions.assertEquals(2, averageCalories.size());

        AverageCalories averageCalories1 = averageCalories.get(0);
        Assertions.assertEquals(1, averageCalories1.getPersonID());
        Assertions.assertEquals("Test user1", averageCalories1.getPersonName());
        Assertions.assertEquals(2, averageCalories1.getAverage());
        Assertions.assertEquals(List.of("test-meal1", "test-meal3"), averageCalories1.getMealNames());

        AverageCalories averageCalories2 = averageCalories.get(1);
        Assertions.assertEquals(2, averageCalories2.getPersonID());
        Assertions.assertEquals("Test user2", averageCalories2.getPersonName());
        Assertions.assertEquals(3, averageCalories2.getAverage());
        Assertions.assertEquals(List.of("test-meal1", "test-meal2", "test-meal3"), averageCalories2.getMealNames());
    }
}
