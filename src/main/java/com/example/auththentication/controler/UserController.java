package com.example.auththentication.controler;


import com.example.auththentication.dto.SignInRequest;
import com.example.auththentication.dto.SignInResponse;
import com.example.auththentication.dto.SignUpRequest;
import com.example.auththentication.dto.SignUpResponse;
import com.example.auththentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        SignUpResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        SignInResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Void> validateToken() {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut() {
        return null;
    }

    @GetMapping("/{userId}")
    public void getUser(@PathVariable Long userId) {
        return;
    }
}
