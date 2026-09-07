package com.example.quipuxproject.pessoa.service.leitura;

import com.example.quipuxproject.nationalize.dto.responseToUser.NacionalidadeResponse;
import com.example.quipuxproject.nationalize.service.NationalizeService;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceLeituraTest {

    @Mock
    private PessoaRepository repository;

    @Mock
    private PessoaMapper mapper;

    @Mock
    private NationalizeService nationalizeService;

    @InjectMocks
    private PessoaServiceLeitura service;

    private Pessoa pessoa;
    private PessoaResponse pessoaResponse;
    private NacionalidadeResponse nacionalidadeResponse;
    private final String CPF_VALIDO = "12345678901";
    private final String NOME_VALIDO = "Lucas";
    private final String PAIS = "br";

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setCpf(CPF_VALIDO);
        pessoa.setNome(NOME_VALIDO);

        pessoaResponse = new PessoaResponse();
        nacionalidadeResponse = new NacionalidadeResponse(NOME_VALIDO, PAIS);
    }

    @Nested
    @DisplayName("Testes do método listAll")
    class ListAllTests {

        @Test
        @DisplayName("Deve retornar lista de pessoas quando houver registros no banco")
        void deveRetornarListaDePessoas() {
            // Arrange
            when(repository.findAll()).thenReturn(List.of(pessoa));
            when(mapper.toResponse(pessoa)).thenReturn(pessoaResponse);

            // Act
            List<PessoaResponse> resultado = service.listAll();

            // Assert
            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            verify(repository, times(1)).findAll();
            verify(mapper, times(1)).toResponse(pessoa);
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver registros")
        void deveRetornarListaVazia() {
            // Arrange
            when(repository.findAll()).thenReturn(Collections.emptyList());

            // Act
            List<PessoaResponse> resultado = service.listAll();

            // Assert
            assertNotNull(resultado);
            assertTrue(resultado.isEmpty());
            verify(repository, times(1)).findAll();
            verify(mapper, never()).toResponse(any());
        }
    }

    @Nested
    @DisplayName("Testes do método listByCpf")
    class ListByCpfTests {

        @Test
        @DisplayName("Deve retornar pessoa quando o CPF for encontrado")
        void deveRetornarPessoaQuandoCpfEncontrado() {
            // Arrange
            when(repository.findByCpf(CPF_VALIDO)).thenReturn(Optional.of(pessoa));
            when(mapper.toResponse(pessoa)).thenReturn(pessoaResponse);

            // Act
            PessoaResponse resultado = service.listByCpf(CPF_VALIDO);

            // Assert
            assertNotNull(resultado);
            verify(repository, times(1)).findByCpf(CPF_VALIDO);
            verify(mapper, times(1)).toResponse(pessoa);
        }

        @Test
        @DisplayName("Deve lançar RuntimeException quando o CPF não for encontrado")
        void deveLancarExcecaoQuandoCpfNaoEncontrado() {
            // Arrange
            when(repository.findByCpf(CPF_VALIDO)).thenReturn(Optional.empty());

            // Act & Assert
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> service.listByCpf(CPF_VALIDO)
            );

            assertEquals("Inválido", exception.getMessage());
            verify(repository, times(1)).findByCpf(CPF_VALIDO);
            verify(mapper, never()).toResponse(any());
        }
    }

    @Nested
    @DisplayName("Testes do método probabilidadeNac")
    class ProbabilidadeNacTests {

        @Test
        @DisplayName("Deve buscar e retornar a probabilidade de nacionalidade com sucesso")
        void deveRetornarProbabilidadeNacionalidadeComSucesso() {
            // Arrange
            when(repository.findByCpf(CPF_VALIDO)).thenReturn(Optional.of(pessoa));
            when(nationalizeService.buscarProvavelNacionalidade(NOME_VALIDO)).thenReturn(nacionalidadeResponse);

            // Act
            NacionalidadeResponse resultado = service.probabilidadeNac(CPF_VALIDO);

            // Assert
            assertNotNull(resultado);
            verify(repository, times(1)).findByCpf(CPF_VALIDO);
            verify(nationalizeService, times(1)).buscarProvavelNacionalidade(NOME_VALIDO);
        }

        @Test
        @DisplayName("Deve lançar RuntimeException ao buscar nacionalidade e não encontrar CPF")
        void deveLancarExcecaoAoBuscarNacionalidadeComCpfInexistente() {
            // Arrange
            when(repository.findByCpf(CPF_VALIDO)).thenReturn(Optional.empty());

            // Act & Assert
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> service.probabilidadeNac(CPF_VALIDO)
            );

            assertEquals("Inválido", exception.getMessage());
            verify(repository, times(1)).findByCpf(CPF_VALIDO);
            verify(nationalizeService, never()).buscarProvavelNacionalidade(any());
        }
    }
}