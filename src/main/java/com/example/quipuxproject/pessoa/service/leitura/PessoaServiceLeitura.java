package com.example.quipuxproject.pessoa.service.leitura;

import com.example.quipuxproject.nationalize.dto.responseToUser.NacionalidadeResponse;
import com.example.quipuxproject.nationalize.service.NationalizeService;
import com.example.quipuxproject.pessoa.dto.response.PessoaResponse;
import com.example.quipuxproject.pessoa.mapper.PessoaMapper;
import com.example.quipuxproject.pessoa.model.Pessoa;
import com.example.quipuxproject.pessoa.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PessoaServiceLeitura {

    // Injeção de dependência
    private final PessoaRepository repository;
    private final PessoaMapper mapper;
    private final NationalizeService nationalizeService;

    // Métodos
    public List<PessoaResponse> listAll(){
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public PessoaResponse listByCpf(String cpf){
        return mapper.toResponse(repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Inválido")));
    }

    public NacionalidadeResponse probabilidadeNac(String cpf){
        Pessoa pessoa = repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Inválido"));

        return nationalizeService.buscarProvavelNacionalidade(pessoa.getNome());
    }

}
