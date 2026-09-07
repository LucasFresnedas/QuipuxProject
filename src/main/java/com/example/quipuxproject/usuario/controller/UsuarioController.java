package com.example.quipuxproject.usuario.controller;

import com.example.quipuxproject.usuario.dto.request.UsuarioRequest;
import com.example.quipuxproject.usuario.dto.response.UsuarioResponse;
import com.example.quipuxproject.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/usuario/")
@AllArgsConstructor
public class UsuarioController {

    // Injeção de dependência
    private final UsuarioService service;

    @PostMapping("registrar")
    public ResponseEntity<UsuarioResponse> create(
            @RequestBody @Valid UsuarioRequest request){
        log.info("Requisição recebida para registrar usuário");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @DeleteMapping("deletar")
    public ResponseEntity<Void> delete(@PathVariable String id){
        log.info("Requisição recebida para deleção de usuário");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
