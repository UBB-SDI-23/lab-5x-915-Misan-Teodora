package com.example.lab1.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Table(name= "personal_data")
public class PersonData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="person_id")
    private Integer person_id;   
    
    @Column(name="email")
    private String email;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;
}
