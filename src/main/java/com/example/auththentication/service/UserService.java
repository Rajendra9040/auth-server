package com.example.auththentication.service;

import com.example.auththentication.dto.SignUpRequest;
import com.example.auththentication.dto.event.UserCreationEvent;
import com.example.auththentication.model.Role;
import com.example.auththentication.model.User;
import com.example.auththentication.model.UserRole;
import com.example.auththentication.repository.RoleRepository;
import com.example.auththentication.repository.UserRepository;
import com.example.auththentication.repository.UserRoleRepository;
import com.example.auththentication.utils.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;


    public User createUser(SignUpRequest request) {
        userRepository.findByEmail(request.getEmail())
            .ifPresent(user -> {
                throw new RuntimeException("User with this email already exists!");
            });

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = SignUpRequest.toUser(request);
        Role role = roleRepository.findByName("USER").orElseThrow();
        User createUser = userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(createUser.getId());
        userRoleRepository.save(userRole);
        EventPublisher.publishEvent(new UserCreationEvent().setUser(createUser));
        return createUser;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(String.format("User not found for email %s", email)));
    }
}
