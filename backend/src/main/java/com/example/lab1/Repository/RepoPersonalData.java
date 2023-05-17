package com.example.lab1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.lab1.Model.PersonData;

public interface RepoPersonalData extends JpaRepository<PersonData, Integer> {
    
}
