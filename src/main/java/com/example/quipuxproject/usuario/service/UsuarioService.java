package com.example.quipuxproject.usuario.service;

import com.example.quipuxproject.exception.LoginJaCadastradoException;
import com.example.quipuxproject.usuario.dto.request.UsuarioRequest;
import com.example.quipuxproject.usuario.dto.response.UsuarioResponse;
import com.example.quipuxproject.usuario.mapper.UsuarioMapper;
import com.example.quipuxproject.usuario.model.Usuario;
import com.example.quipuxproject.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UsuarioService {

    // Injeção de dependência
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioResponse create(UsuarioRequest request){
        log.info("Iniciando validação de duplicidade de pessoa");
        if (repository.existsByLogin(request.getLogin())){
            throw new LoginJaCadastradoException("Login já existente...");
        }
        Usuario usuario = mapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        log.info("Registrando novo usuario no MongoDB...");
        repository.save(usuario);
        return mapper.toResponse(usuario);
    }

    public void delete(String id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inválido"));
        repository.delete(usuario);
    }

}
