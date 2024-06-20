package com.example.auththentication.service;

import com.example.auththentication.config.ApplicationConfig;
import com.example.auththentication.config.JwtService;
import com.example.auththentication.dto.SignInRequest;
import com.example.auththentication.dto.SignInResponse;
import com.example.auththentication.dto.SignUpRequest;
import com.example.auththentication.dto.SignUpResponse;
import com.example.auththentication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final ApplicationConfig applicationConfig;

    public SignUpResponse register(SignUpRequest request) {
        User user = userService.createUser(request);
        String jwtToken = jwtService.generateToken(applicationConfig.createUserDetails(user));

        return SignUpResponse.builder()
            .token(jwtToken)
            .message("User registered successfully!")
            .build();
    }

    public SignInResponse login (SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userService.getUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(applicationConfig.createUserDetails(user));

        return SignInResponse.builder()
            .token(jwtToken)
            .message("User logged in successfully!")
            .build();
    }
}
