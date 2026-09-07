package com.example.quipuxproject.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String login;
    private String token;
    private String tipo = "Bearer";

    // Construtor
    public LoginResponse(
            String login,
            String token){
        this.login = login;
        this.token = token;
    }
}
