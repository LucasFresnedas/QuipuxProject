package com.example.quipuxproject.security.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Chave secreta em hardcode (deve possuir no mínimo 32 caracteres / 256 bits)
    private static final String SECRET = "chave_teste_da_quipux123456inseridahardcode"; // Inseri a chave hardcode para facilitar a rodagem local quando feito o clone

    // Tempo de expiração (1 hora)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // Converte a String hardcoded na SecretKey tratada pelo JJWT
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // Cria um token
    public String gerarToken(String login, String userId) {
        return Jwts.builder()
                .subject(login)
                .claim("id", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Valida o Token
    public Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}