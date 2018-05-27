package pl.coderslab.sports_betting.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data
class MatchDto {

    @JsonProperty("match_id")
    long id;
    @JsonProperty("country_name")
    String country;
    @JsonProperty("league_name")
    String league;
    @JsonProperty("match_date")
    String date;
    @JsonProperty("match_time")
    String time;
    @JsonProperty("match_status")
    String status;
    @JsonProperty("match_hometeam_name")
    String homeTeam;
    @JsonProperty("match_awayteam_name")
    String awayTeam;
    @JsonProperty("match_hometeam_score")
    String homeScore;
    @JsonProperty("match_hometeam_halftime_score")
    String homeHalfScore;
    @JsonProperty("match_awayteam_score")
    String awayScore;
    @JsonProperty("match_awayteam_halftime_score")
    String awayHalfScore;
    @JsonProperty("league_id")
    long league_id;

}
