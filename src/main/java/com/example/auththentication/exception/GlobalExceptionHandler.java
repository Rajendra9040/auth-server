package com.example.auththentication.exception;

import com.example.auththentication.config.CustomeAccessDeniedHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws ServletException, IOException {
        log.error("Access denied!");
        new CustomeAccessDeniedHandler().handle(request, response, ex);
    }
}
