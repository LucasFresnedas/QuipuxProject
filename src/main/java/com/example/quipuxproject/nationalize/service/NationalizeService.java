package com.example.quipuxproject.nationalize.service;

import com.example.quipuxproject.nationalize.dto.responseFromApi.NationalizeResponse;
import com.example.quipuxproject.nationalize.dto.responseToUser.NacionalidadeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Comparator;

@Service
@Slf4j
public class NationalizeService {

    private final RestClient restClient = RestClient.create();

    public NacionalidadeResponse buscarProvavelNacionalidade(String nome) {
        log.info("Buscando probabilidade de nacionalidade para o nome: {}", nome);

        String url = "https://api.nationalize.io/?name=" + nome;

        NationalizeResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(NationalizeResponse.class);

        // Trata chamadas vazias
        if (response == null || response.getCountry() == null || response.getCountry().isEmpty()) {
            log.warn("Nenhuma nacionalidade encontrada para o nome: {}", nome);
            return new NacionalidadeResponse(nome, "DESCONHECIDO");
        }

        // Compara maior probabilidade
        NationalizeResponse.CountryDto maiorProbabilidade = response.getCountry().stream()
                .max(Comparator.comparing(NationalizeResponse.CountryDto::getProbability))
                .orElse(response.getCountry().get(0));

        log.info("Maior probabilidade para {}: {} ({})", nome, maiorProbabilidade.getCountryId(), maiorProbabilidade.getProbability());

        // Retorna nome e país com maior probabilidade
        return new NacionalidadeResponse(
                nome,
                maiorProbabilidade.getCountryId()
        );
    }
}
