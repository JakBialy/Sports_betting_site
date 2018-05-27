package pl.coderslab.sports_betting.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
public @Data
class CountryDto {

    @JsonProperty("country_id")
    long apiCountryId;
    @JsonProperty("country_name")
    String name;
}