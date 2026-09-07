package com.example.quipuxproject.auth.controller;


import com.example.quipuxproject.auth.dto.request.LoginRequest;
import com.example.quipuxproject.auth.dto.response.LoginResponse;
import com.example.quipuxproject.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    // Injeção de dependência
    private AuthService authService;

    // Métodos
    @PostMapping
    public ResponseEntity<LoginResponse> autentificacao(
            @RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok(authService.autenticar(loginRequest));
    }
}
