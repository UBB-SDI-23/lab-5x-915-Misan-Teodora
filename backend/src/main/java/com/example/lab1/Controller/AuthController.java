package com.example.lab1.Controller;

import com.example.lab1.Model.Person;
import com.example.lab1.Model.PersonData;
import com.example.lab1.Service.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @RequestMapping( value = "/register",method = RequestMethod.POST )
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest register) {

        return new ResponseEntity<>(authenticationService.register(register), HttpStatus.OK);
    }

    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {

        return new ResponseEntity<>(authenticationService.authenticate(authenticationRequest), HttpStatus.OK);
    }

    @RequestMapping( value = "/confirm/{activation_code}", method = RequestMethod.POST )
    public ResponseEntity<Person> confirmRegistration(@PathVariable String activation_code) {

        return new ResponseEntity<>(authenticationService.activate(activation_code), HttpStatus.OK);
    }
}
