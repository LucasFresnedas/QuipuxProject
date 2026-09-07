package com.example.quipuxproject.pessoa.controller;

import com.example.quipuxproject.nationalize.dto.responseToUser.NacionalidadeResponse;
import com.example.quipuxproject.pessoa.dto.request.PessoaRequest;
import com.example.quipuxproject.pessoa.dto.response.PessoaResponse;
import com.example.quipuxproject.pessoa.service.escrita.PessoaServiceEscrita;
import com.example.quipuxproject.pessoa.service.leitura.PessoaServiceLeitura;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/quipux/")
@SecurityRequirement(name = "bearerAuth")
public class PessoaController {

    // Injeção de dependência
    private final PessoaServiceEscrita writer;
    private final PessoaServiceLeitura reader;

    /*

    Serviços de escrita

     */


    // Definição do endpoint conforme teste
    @PostMapping("registrarName")
    public ResponseEntity<PessoaResponse> create(
            @RequestBody @Valid PessoaRequest request){
        log.info("Requisição recebida em /registrarName");
        return ResponseEntity.status(HttpStatus.CREATED).body(writer.createPessoa(request));
    }

    // Definição do endpoint conforme teste
    @DeleteMapping("list/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf){
        log.info("Requisição recebida para deleção");
        writer.deletePessoa(cpf);
        return ResponseEntity.noContent().build();
    }

    /*

    Serviços de leitura

     */

    @GetMapping("list")
    public ResponseEntity<List<PessoaResponse>> listAll(){
        log.info("Requisição recebida em listagem");
        return ResponseEntity.ok(reader.listAll());
    }

    @GetMapping("list/{cpf}")
    public ResponseEntity<PessoaResponse> listByCpf(@PathVariable String cpf){
        log.info("Requisição recebida em listagem de retorno único");
        return ResponseEntity.ok(reader.listByCpf(cpf));
    }

    @GetMapping("findNacionalityByPerson/{cpf}")
    public ResponseEntity<NacionalidadeResponse> probabilidadeNac(@PathVariable String cpf){
        log.info("Requisição recebida para verificar a probabilidade de nascimento");
        return ResponseEntity.ok(reader.probabilidadeNac(cpf));
    }
}
