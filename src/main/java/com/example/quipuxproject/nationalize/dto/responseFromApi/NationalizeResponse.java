package com.example.quipuxproject.nationalize.dto.responseFromApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NationalizeResponse {
    private String name;
    private int count;
    private List<CountryDto> country;

    @Data
    public static class CountryDto {
        @JsonProperty("country_id")
        private String countryId;
        private Double probability;
    }
}
