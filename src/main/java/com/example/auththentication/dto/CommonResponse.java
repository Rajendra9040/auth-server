package com.example.auththentication.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Accessors(chain = true)
public class CommonResponse {
    private String message;
    private HttpStatus status;
}
