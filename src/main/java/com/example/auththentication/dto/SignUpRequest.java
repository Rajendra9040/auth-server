package com.example.auththentication.dto;

import com.example.auththentication.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SignUpRequest {
    private String name;
    private String email;
    private String password;

    public static User toUser (SignUpRequest request) {
        return User
            .builder()
            .email(request.getEmail())
            .hashedPassword(request.getPassword())
            .name(request.getName())
            .build();
    }
}
