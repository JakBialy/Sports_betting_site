package pl.coderslab.sports_betting.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data
class LeagueDto {


    @JsonProperty("league_id")
    long league_id;
    @JsonProperty("league_name")
    String league_name;
    @JsonProperty("country_name")
    String country_name;
    @JsonProperty("country_id")
    long country_id;
}
