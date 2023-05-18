package com.example.lab1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.lab1.Model.Person;

import java.util.Optional;

public interface RepoPers extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
