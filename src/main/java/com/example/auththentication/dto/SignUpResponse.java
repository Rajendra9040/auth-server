package com.example.auththentication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignUpResponse {
    private String token;
    private String message;
}
