package com.example.quipuxproject.usuario.repository;

import com.example.quipuxproject.usuario.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByLogin(String login);
    boolean existsByLogin(String login);
}
