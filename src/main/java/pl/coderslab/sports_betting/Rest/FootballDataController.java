package pl.coderslab.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.sports_betting.Dto.CountryDto;


@RestController
@RequestMapping("/admin")
public class FootballDataController {

//    @Autowired
//    CountryDao countryDao;
//
//    @Autowired
//    LeagueDao leagueDao;
//
//    @Autowired
//    TeamDao teamDao;
//
//    @Autowired
//    MatchDao matchDao;
//
//    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
//
    // adds countries from external API to internal Database
//    @RequestMapping("/get-countries")
//    public String getCountriesAction() {
//        String url = "https://apifootball.com/api/?action=get_countries&APIkey=eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<CountryDto[]> responseCountries = restTemplate.getForEntity(url, CountryDto[].class);
//        CountryDto[] countries = responseCountries.getBody();
//
//        // saves countries to db
//        countryDao.addAll(countries);
//
//        return "List of countries saved to DB";
//    }
//
//    // adds leagues from external API to internal Database
//
//    @RequestMapping("/get-leagues")
//    public String getLeaguesAction() {
//        String url = "https://apifootball.com/api/?action=get_leagues&APIkey=d1854882fb332d85350cea5b65c0f4df0abc14817d39b44087d478a921ee1515";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<LeagueDto[]> responseCountries = restTemplate.getForEntity(url, LeagueDto[].class);
//        LeagueDto[] leagues = responseCountries.getBody();
//
//        // saves leagues to db
//        leagueDao.addAll(leagues);
//
//        return "List of leagues saved to DB";
//    }
//
//    //get teams from external API to internal Database
//
//    @RequestMapping("/get-teams")
//    public String getTeamsAction() {
//
//        String url = "https://apifootball.com/api/?action=get_standings&league_id=63&APIkey=d1854882fb332d85350cea5b65c0f4df0abc14817d39b44087d478a921ee1515";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<TeamDto[]> responseTeams = restTemplate.getForEntity(url, TeamDto[].class);
//        TeamDto[] teams = responseTeams.getBody();
//
//        // saves teams to db
//        teamDao.addAll(teams);
//
//        String url1 = "https://apifootball.com/api/?action=get_standings&league_id=128&APIkey=d1854882fb332d85350cea5b65c0f4df0abc14817d39b44087d478a921ee1515";
//
//        RestTemplate restTemplate1 = new RestTemplate();
//        ResponseEntity<TeamDto[]> responseTeams1 = restTemplate1.getForEntity(url1, TeamDto[].class);
//        TeamDto[] teams1 = responseTeams1.getBody();
//
//        // saves teams to db
//        teamDao.addAll(teams1);
//        return "List of leagues saved to DB";
//    }
//
//    @RequestMapping("/get-matches")
//    public String getMatchesAction() {
//
//        String url = "https://apifootball.com/api/?action=get_events&from=2018-04-20&to=2018-11-01&league_id=63&APIkey=d1854882fb332d85350cea5b65c0f4df0abc14817d39b44087d478a921ee1515";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<MatchDto[]> responseMatches = restTemplate.getForEntity(url, MatchDto[].class);
//        MatchDto[] matches = responseMatches.getBody();
//
//        // saves matches to db
//        matchDao.addAll(matches);
//
//        String url1 = "https://apifootball.com/api/?action=get_events&from=2018-04-20&to=2018-11-01&league_id=128&APIkey=d1854882fb332d85350cea5b65c0f4df0abc14817d39b44087d478a921ee1515";
//
//        RestTemplate restTemplate1 = new RestTemplate();
//        ResponseEntity<MatchDto[]> responseMatches1 = restTemplate1.getForEntity(url1, MatchDto[].class);
//        MatchDto[] matches1 = responseMatches1.getBody();
//
//        // saves matches to db
//        matchDao.addAll(matches1);
//
//        return "List of matches saved to DB";
//    }

}