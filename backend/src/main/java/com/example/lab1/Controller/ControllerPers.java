package com.example.lab1.Controller;

import java.util.List;

import com.example.lab1.Model.dto.PersonMealPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.lab1.Model.Person;
import com.example.lab1.Model.dto.PersonMeals;
import com.example.lab1.Service.PersonsService;

@RestController
@RequestMapping(path = "/api/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerPers {

    @Autowired
    private PersonsService personsService;

    @GetMapping("")
    public Page<Person> getMeals(@PageableDefault Pageable pageable) {
        return personsService.getAllPersons(pageable);
    }

    @GetMapping("/{id}")
    public PersonMealPlan getMealsPerPerson(@PathVariable Integer id, @PageableDefault Pageable pageable) {
        return personsService.getMealsPerPerson(id, pageable);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Person> getMealByID(@PathVariable Integer id) {
    //     return personsService.getByID(id);
    // }

    @PostMapping
    public ResponseEntity<Person> addPers(@RequestBody Person person) {
            return personsService.add(person);
    }

    @PutMapping
    public ResponseEntity<Person> updateMeal(@RequestBody Person meal) {
        return personsService.update(meal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable Integer id) {
        personsService.delete(id);
    }
}
