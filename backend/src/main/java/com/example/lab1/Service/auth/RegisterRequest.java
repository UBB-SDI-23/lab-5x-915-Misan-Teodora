package com.example.lab1.Service.auth;

import com.example.lab1.security.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private ERole role = ERole.ROLE_USER;
    private String country;
    private String city;
    private Integer age;
    private Integer kg;
    private Integer height;
    private String email;

}
