package com.example.lab1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.lab1.Model.Person;

public interface RepoPers extends JpaRepository<Person, Integer> {
    
}
