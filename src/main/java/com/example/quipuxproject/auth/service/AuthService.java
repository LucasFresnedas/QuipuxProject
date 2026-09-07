package com.example.quipuxproject.auth.service;

import com.example.quipuxproject.auth.dto.request.LoginRequest;
import com.example.quipuxproject.auth.dto.response.LoginResponse;
import com.example.quipuxproject.security.jwt.service.JwtService;
import com.example.quipuxproject.usuario.model.Usuario;
import com.example.quipuxproject.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Autenticador
    public LoginResponse autenticar(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByLogin(loginRequest.getLogin())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }
        String token = jwtService.gerarToken(usuario.getLogin(), usuario.getId());
        return new LoginResponse(usuario.getLogin(), token);
    }
}
