package com.example.lab1.Repository;

import com.example.lab1.Model.Meal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface Repo extends CrudRepository<Meal, Integer> {

    @Query("SELECT meal FROM Meal meal WHERE meal.personId = :personId")
    List<Meal> findByPersonID(@Param("personId") String personId);

    @Query("SELECT meal FROM Meal meal WHERE meal.calories > :calories")
    List<Meal> filterByCalories(@Param("calories") String calories);
}
