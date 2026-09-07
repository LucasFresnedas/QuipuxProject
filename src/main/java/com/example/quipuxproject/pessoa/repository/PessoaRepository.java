package com.example.quipuxproject.pessoa.repository;

import com.example.quipuxproject.pessoa.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    Optional<Pessoa> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
