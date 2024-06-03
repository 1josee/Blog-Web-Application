package com.tien.blogapplication.controller;

import com.tien.blogapplication.model.User;
import com.tien.blogapplication.request.AuthenticationRequest;
import com.tien.blogapplication.response.AuthenticationResponse;
import com.tien.blogapplication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User user
    ){
        AuthenticationResponse authenticationResponse = authenticationService.register(user);
        return ResponseEntity.ok(authenticationResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
            ){
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }
}
