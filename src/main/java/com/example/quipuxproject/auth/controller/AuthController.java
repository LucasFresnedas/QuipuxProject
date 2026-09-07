package com.example.quipuxproject.auth.controller;


import com.example.quipuxproject.auth.dto.request.LoginRequest;
import com.example.quipuxproject.auth.dto.response.LoginResponse;
import com.example.quipuxproject.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.info("Requisição recebida na camada de autentificação");
        return ResponseEntity.ok(authService.autenticar(loginRequest));
    }
}
