package com.example.lab1.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.lab1.Model.PersonData;
import com.example.lab1.Repository.RepoPersonalData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalDataService {
    private final RepoPersonalData dataRepo;

    public List<PersonData> getAllPersonalData() {
        return StreamSupport.stream(dataRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ResponseEntity<PersonData> getByID(Integer id) {
        log.info("Get by id: {} invoked.", id);
        Optional<PersonData> person =  dataRepo.findById(id);
        if(person.isEmpty()) {
            log.info("Unable to find data for personal data with id: {}.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person.get(), HttpStatus.OK);
    }

    public ResponseEntity<PersonData> add(PersonData person) {
        log.info("POST method invoked.");
        return new ResponseEntity<>(dataRepo.save(person), HttpStatus.CREATED);
    }

    public ResponseEntity<PersonData> update(PersonData person) {
        log.info("PUT method invoked.");
        Optional<PersonData> existent =  dataRepo.findById(person.getId());
        if(existent.isEmpty()) {
            log.info("Unable to update person data with id: {}. It was not found.", person.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dataRepo.save(person), HttpStatus.OK);
    }

    public void delete(Integer id) {
        log.info("DELETE method invoked.");
        dataRepo.deleteById(id);
    }
}
