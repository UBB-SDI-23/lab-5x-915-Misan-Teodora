package com.example.lab1.Service;

import com.example.lab1.Model.dto.PersonMealPlan;
import com.example.lab1.Repository.Repo;
import com.example.lab1.Repository.RepoPers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.lab1.Model.Meal;
import com.example.lab1.Model.Person;
import com.example.lab1.Model.dto.PersonMeals;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonsService {
    private final RepoPers personsRepo;
    private final Repo nutritionRepo;

    public Page<Person> getAllPersons(Pageable pageable) {
        return personsRepo.findAll(pageable);
    }

    public ResponseEntity<Person> getByID(Integer id) {
        log.info("Get by id: {} invoked.", id);
        Optional<Person> person =  personsRepo.findById(id);
        if(person.isEmpty()) {
            log.info("Unable to find person with id: {}.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    public PersonMealPlan getMealsPerPerson(Integer id, Pageable pageable) {
        Page<Meal> mealsPerPerson =  nutritionRepo.findByPersonId(id, pageable);

        Person person = personsRepo.findById(id).orElseThrow();
        return new PersonMealPlan(person.getId(), person.getName(), mealsPerPerson);
    }

    public ResponseEntity<Person> add(Person person) {
        log.info("POST method invoked.");
        return new ResponseEntity<>(personsRepo.save(person), HttpStatus.CREATED);
    }

    public ResponseEntity<Person> update(Person person) {
        log.info("PUT method invoked.");
        Optional<Person> existent =  personsRepo.findById(person.getId());
        if(existent.isEmpty()) {
            log.info("Unable to update person with id: {}. It was not found.", person.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personsRepo.save(person), HttpStatus.OK);
    }

    public void delete(Integer id) {
        log.info("DELETE method invoked.");
        personsRepo.deleteById(id);
    }
}