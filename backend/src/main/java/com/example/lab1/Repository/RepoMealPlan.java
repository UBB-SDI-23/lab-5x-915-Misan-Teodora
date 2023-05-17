package com.example.lab1.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.lab1.Model.MealPlan;

import java.util.List;

public interface RepoMealPlan extends JpaRepository<MealPlan, Integer> {

    Page<MealPlan> findAllByPersonId(Integer person_id, Pageable pageable);
}
