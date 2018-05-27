package pl.coderslab.sports_betting.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)

public @Data
class TeamDto {

    @JsonProperty("team_name")
    String team_name;
    @JsonProperty("country_name")
    String country_name;
    @JsonProperty("league_name")
    String league_name;
    @JsonProperty("overall_league_position")
    int league_position;
    @JsonProperty("overall_league_W")
    int wins;
    @JsonProperty("overall_league_D")
    int draws;
    @JsonProperty("overall_league_L")
    int lost;
    @JsonProperty("league_id")
    long league_id;
}
