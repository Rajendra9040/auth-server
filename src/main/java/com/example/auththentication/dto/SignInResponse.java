package com.example.auththentication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignInResponse {
    private String token;
    private String message;
}
