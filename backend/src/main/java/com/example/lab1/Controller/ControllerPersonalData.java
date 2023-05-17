package com.example.lab1.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab1.Model.PersonData;
import com.example.lab1.Service.PersonalDataService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/api/personaldata", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerPersonalData {
    @Autowired
    private PersonalDataService personsService;

    @GetMapping("/status")
    public String status() {
        return "running";
    }

    @GetMapping("")
    public List<PersonData> getMeals() {
        return personsService.getAllPersonalData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonData> getByID(@PathVariable Integer id) {
        return personsService.getByID(id);
    }

    @PostMapping
    public ResponseEntity<PersonData> add(@RequestBody PersonData person) {
        return personsService.add(person);
    }

    @PutMapping
    public ResponseEntity<PersonData> update(@RequestBody PersonData meal) {
        return personsService.update(meal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        personsService.delete(id);
    }
}
