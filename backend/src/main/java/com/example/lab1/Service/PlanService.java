package com.example.lab1.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

import com.example.lab1.Model.dto.PersonMealPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.lab1.Model.Meal;
import com.example.lab1.Model.MealPlan;
import com.example.lab1.Model.Person;
import com.example.lab1.Model.dto.AverageCalories;
import com.example.lab1.Model.dto.PersonMeals;
import com.example.lab1.Repository.Repo;
import com.example.lab1.Repository.RepoMealPlan;
import com.example.lab1.Repository.RepoPers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {
    private final RepoMealPlan planRepo;
    private final RepoPers persRepo;
    private final Repo mealRepo;

    public Page<MealPlan> getAllPlans(Pageable pageable) {
        return planRepo.findAll(pageable);
    }

    public ResponseEntity<MealPlan> getByID(Integer id) {
        log.info("Get by id: {} invoked.", id);
        Optional<MealPlan> plan =  planRepo.findById(id);
        if(plan.isEmpty()) {
            log.info("Unable to find plan with id: {}.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(plan.get(), HttpStatus.OK);
    }

    public List<PersonMeals> statisticalFilter() {
        List<MealPlan> mealPlans = new ArrayList<>();
        mealPlans = StreamSupport.stream(planRepo.findAll(Pageable.ofSize(10000)).spliterator(), false)
                        .collect(Collectors.toList());

        Map<Integer, List<MealPlan>> mealsGroupedByPerson =  mealPlans.stream()
                .collect(Collectors.groupingBy(MealPlan::getPersonId));

        List<PersonMeals> personsMeals = new ArrayList<>();

        mealsGroupedByPerson.keySet()
                .forEach(personId -> {
                    Person person = persRepo.findById(personId).orElseThrow();
                    List<Meal> listMeals = mealsGroupedByPerson.get(personId).stream()
                            .map(mealPlan -> mealRepo.findById(mealPlan.getMeal_id()).orElseThrow())
                            .toList();

                    personsMeals.add(new PersonMeals(personId, person.getName(), listMeals));
                });

        return personsMeals
            .stream()
            .sorted((mealPlan1, mealPlan2) -> {
                return mealPlan2.getMeals().size() - mealPlan1.getMeals().size();
            })
            .limit(10)
            .toList();
    }

    public List<AverageCalories> getStatisticalCalories(Pageable pageable) {
        return StreamSupport.stream(persRepo.findAll(pageable).spliterator(), false)
            .map(this::getAverageCaloriesByUser)
            .sorted((person1, person2) -> (int) (person1.getAverage() - person2.getAverage()))
            .toList();
    }

    public AverageCalories getAverageCaloriesByUser(Person person) {
        List<MealPlan> mealPlans = planRepo.findAllByPersonId(person.getId(), Pageable.unpaged()).getContent();

        List<String> mealNames = mealPlans.stream()
                                    .map(mealPlan -> mealRepo.findById(mealPlan.getMeal_id()).get().getName())
                                    .toList();
        
        Double calorieAvg  = mealPlans.stream()
                                .mapToInt(mealPlan -> mealRepo.findById(mealPlan.getMeal_id()).get().getCalories())
                                .average()
                                .orElse(0);

        return new AverageCalories(person.getId(), person.getName(), mealNames, calorieAvg);
    }


    public ResponseEntity<MealPlan> add(MealPlan plan) {
        log.info("POST method invoked.");
        return new ResponseEntity<>(planRepo.save(plan), HttpStatus.CREATED);
    }

    public ResponseEntity<MealPlan> update(MealPlan meal) {
        log.info("PUT method invoked.");
        Optional<MealPlan> existent =  planRepo.findById(meal.getId());
        if(existent.isEmpty()) {
            log.info("Unable to update plan with id: {}. It was not found.", meal.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(planRepo.save(meal), HttpStatus.OK);
    }

    public void delete(Integer id) {
        log.info("DELETE method invoked.");
        planRepo.deleteById(id);
    }

    public ResponseEntity<PersonMealPlan> getMealPlansByUserId(Integer id, Pageable pageable) {

        Page<Meal> meals = planRepo.findAllByPersonId(id, pageable)
                .map(mealPlan -> mealRepo.findById(mealPlan.getMeal_id()).get());

        return new ResponseEntity<>(new PersonMealPlan(id, "", meals), HttpStatus.OK);
    }
}
