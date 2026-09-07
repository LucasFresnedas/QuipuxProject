package com.example.quipuxproject.pessoa.service.escrita;

import com.example.quipuxproject.exception.CpfJaCadastradoException;
import com.example.quipuxproject.pessoa.dto.request.PessoaRequest;
import com.example.quipuxproject.pessoa.dto.response.PessoaResponse;
import com.example.quipuxproject.pessoa.mapper.PessoaMapper;
import com.example.quipuxproject.pessoa.model.Pessoa;
import com.example.quipuxproject.pessoa.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PessoaServiceEscrita {

    // Injeção de dependência
    private final PessoaRepository repository;
    private final PessoaMapper mapper;

    // Métodos
    public PessoaResponse createPessoa(PessoaRequest request){

        // Validação
        log.info("Iniciando validação de duplicidade de pessoa");
        if (repository.existsByCpf(request.getCpf())){
            throw new CpfJaCadastradoException("CPF existente no bd...");
        }

        Pessoa pessoa = mapper.toEntity(request);
        log.info("Registrando nova pessoa no MongoDB...");
        repository.save(pessoa);
        return mapper.toResponse(pessoa);
    }

    public void deletePessoa(String cpf){
        Pessoa pessoa = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Inválido"));
        repository.delete(pessoa);
    }
}
