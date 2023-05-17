package com.example.lab1.Repository;

import com.example.lab1.Model.Meal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Repo extends JpaRepository<Meal, Integer> {

    Page<Meal> findByPersonId(Integer personId, Pageable pageable);

    @Query("SELECT meal FROM Meal meal WHERE meal.calories > :calories")
    Page<Meal> filterByCalories(@Param("calories") String calories, Pageable pageable);
}
