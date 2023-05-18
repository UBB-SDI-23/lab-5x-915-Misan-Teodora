package com.example.lab1.Service.auth;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {
    private String activationCode;
}