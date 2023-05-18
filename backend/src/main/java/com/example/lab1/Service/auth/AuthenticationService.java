package com.example.lab1.Service.auth;

import com.example.lab1.Model.Person;
import com.example.lab1.Model.PersonData;
import com.example.lab1.Repository.RepoPers;
import com.example.lab1.Repository.RepoPersonalData;
import com.example.lab1.config.security.UserDetailsImpl;
import com.example.lab1.config.security.jwt.JwtUtils;
import com.example.lab1.security.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RepoPers repository;
    private final RepoPersonalData repoPersonalData;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;

    private final HashMap<String, RegisterRequest> pendingUsers = new HashMap<>();

    public RegisterResponse register(RegisterRequest request) {
        var user = new Person();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        var jwtToken = jwtService.generateActivationCode(UserDetailsImpl.build(user));

        pendingUsers.put(jwtToken, request);

        return RegisterResponse.builder()
                .activationCode(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(UserDetailsImpl.build(user));
        var refreshToken = jwtService.generateRefreshToken(UserDetailsImpl.build(user));

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Person activate(String activationCode) {
        RegisterRequest request = pendingUsers.get(activationCode);

        Person person = new Person();
        person.setName(request.getName());
        person.setAge(request.getAge());
        person.setKg(request.getKg());
        person.setHeight(request.getHeight());
        person.setUsername(request.getUsername());
        person.setPassword(passwordEncoder.encode(request.getPassword()));
        person.setRole(ERole.ROLE_USER);

        Person savedPerson = repository.save(person);

        PersonData personData = new PersonData();
        personData.setCity(request.getCity());
        personData.setCountry(request.getCountry());
        personData.setEmail(request.getEmail());
        personData.setPerson_id(savedPerson.getId());

        repoPersonalData.save(personData);

        pendingUsers.remove(activationCode);
        return savedPerson;
    }
}
