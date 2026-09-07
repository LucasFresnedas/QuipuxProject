package com.example.quipuxproject.pessoa.service.escrita;

import com.example.quipuxproject.exception.CpfJaCadastradoException;
import com.example.quipuxproject.pessoa.dto.request.PessoaRequest;
import com.example.quipuxproject.pessoa.dto.response.PessoaResponse;
import com.example.quipuxproject.pessoa.mapper.PessoaMapper;
import com.example.quipuxproject.pessoa.model.Pessoa;
import com.example.quipuxproject.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceEscritaTest {

    @Mock
    private PessoaRepository repository;

    @Mock
    private PessoaMapper mapper;

    @InjectMocks
    private PessoaServiceEscrita service;

    private PessoaRequest request;
    private Pessoa pessoa;
    private PessoaResponse response;
    private final String CPF_VALIDO = "12345678901";

    @BeforeEach
    void setUp() {
        request = new PessoaRequest();
        request.setCpf(CPF_VALIDO);

        pessoa = new Pessoa();
        pessoa.setCpf(CPF_VALIDO);

        response = new PessoaResponse();
    }

    @Nested
    @DisplayName("Testes do método createPessoa")
    class CreatePessoaTests {

        @Test
        @DisplayName("Deve criar pessoa com sucesso quando o CPF não estiver cadastrado")
        void deveCriarPessoaComSucesso() {
            // Arrange
            when(repository.existsByCpf(CPF_VALIDO)).thenReturn(false);
            when(mapper.toEntity(request)).thenReturn(pessoa);
            when(repository.save(pessoa)).thenReturn(pessoa);
            when(mapper.toResponse(pessoa)).thenReturn(response);

            // Act
            PessoaResponse resultado = service.createPessoa(request);

            // Assert
            assertNotNull(resultado);
            verify(repository, times(1)).existsByCpf(CPF_VALIDO);
            verify(mapper, times(1)).toEntity(request);
            verify(repository, times(1)).save(pessoa);
            verify(mapper, times(1)).toResponse(pessoa);
        }

        @Test
        @DisplayName("Deve lançar CpfJaCadastradoException quando o CPF já existir no banco")
        void deveLancarExcecaoQuandoCpfJaExistir() {
            // Arrange
            when(repository.existsByCpf(CPF_VALIDO)).thenReturn(true);

            // Act & Assert
            CpfJaCadastradoException exception = assertThrows(
                    CpfJaCadastradoException.class,
                    () -> service.createPessoa(request)
            );

            assertEquals("CPF existente no bd...", exception.getMessage());
            verify(repository, times(1)).existsByCpf(CPF_VALIDO);
            verify(repository, never()).save(any());
            verify(mapper, never()).toEntity(any());
        }
    }

    @Nested
    @DisplayName("Testes do método deletePessoa")
    class DeletePessoaTests {

        @Test
        @DisplayName("Deve deletar pessoa com sucesso quando o CPF for encontrado")
        void deveDeletarPessoaComSucesso() {
            // Arrange
            when(repository.findByCpf(CPF_VALIDO)).thenReturn(Optional.of(pessoa));

            // Act
            service.deletePessoa(CPF_VALIDO);

            // Assert
            verify(repository, times(1)).findByCpf(CPF_VALIDO);
            verify(repository, times(1)).delete(pessoa);
        }

        @Test
        @DisplayName("Deve lançar RuntimeException quando o CPF não for encontrado para deleção")
        void deveLancarExcecaoQuandoCpfNaoEncontradoParaDelecao() {
            // Arrange
            when(repository.findByCpf(CPF_VALIDO)).thenReturn(Optional.empty());

            // Act & Assert
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> service.deletePessoa(CPF_VALIDO)
            );

            assertEquals("Inválido", exception.getMessage());
            verify(repository, times(1)).findByCpf(CPF_VALIDO);
            verify(repository, never()).delete(any());
        }
    }
}