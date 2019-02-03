package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Football.FootballLeague;
import pl.coderslab.sports_betting.Entity.Football.FootballTeam;
import pl.coderslab.sports_betting.Repository.Football.FootballLeagueRepository;
import pl.coderslab.sports_betting.Repository.Football.FootballTeamRepository;
import pl.coderslab.sports_betting.Service.Football.Service.FootballTeamService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootballTeamServiceImpl implements FootballTeamService {
    private final FootballLeagueRepository footballLeagueRepository;
    private final FootballTeamRepository footballTeamRepository;

    @Autowired
    public FootballTeamServiceImpl(FootballLeagueRepository footballLeagueRepository, FootballTeamRepository footballTeamRepository) {
        this.footballLeagueRepository = footballLeagueRepository;
        this.footballTeamRepository = footballTeamRepository;
    }

    /**
     * Method is populating database witch teams
     * In this form it is looking for two leagues
     * and it is populating databse them with 10 random named for each one
     * in this case names are from LOTR but can be form whatever source
     */
    public void populateDb(){
        List<FootballTeam> list = new ArrayList<>();
        FootballLeague footballLeague = new FootballLeague();
        Faker faker	= new Faker();

        for (int i = 1; i <=2 ; i++) {
            if (i == 1) {
                 footballLeague = footballLeagueRepository.findOneByName("Somewhership");
            } else{
                footballLeague = footballLeagueRepository.findOneByName("Randomship");
            }
            for (int j = 1; j <=10 ; j++) {
            FootballTeam footballTeam = new FootballTeam();
            footballTeam.setFootballLeague(footballLeague);
            footballTeam.setTeam("FC " + faker.lordOfTheRings().location());

            list.add(footballTeam);
            }
        }

        footballTeamRepository.saveAll(list);
    }

    /**
     * Method is looking for all football teams
     * @return list of football teams
     */
    public List<FootballTeam> allTeams(){
        return footballTeamRepository.findAll();
    }

    /**
     * Method is looking for teams by league id and set them in order by position
     * @param leagueId Id of league
     * @return list of teams by league in order
     */
    public List<FootballTeam> findTeamsByLeagueId(Long leagueId){
        return footballTeamRepository.findAllByFootballLeagueIdOrderByPosition(leagueId);
    }

    /**
     * Method is looking for specific team by team id
     * @param teamID team id
     * @return team
     */
    public FootballTeam findTeamById(Long teamID){
        return footballTeamRepository.getOne(teamID);
    }
}
