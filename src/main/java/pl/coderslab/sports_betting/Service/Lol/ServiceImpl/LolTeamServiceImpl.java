package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;
import pl.coderslab.sports_betting.Repository.Lol.LolLeagueRepository;
import pl.coderslab.sports_betting.Repository.Lol.LolTeamRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.LolTeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LolTeamServiceImpl implements LolTeamService {

    @Autowired
    LolLeagueRepository lolLeagueRepository;

    @Autowired
    LolTeamRepository lolTeamRepository;

    /**
     * Method is populating database with teams
     * In this form it is looking for two leagues
     * and it is populating databse them with 10 random named for each one
     * in this case names are from eSports but can be form whatever source
     */
    public void populateDb(){
        List<LolTeam> list = new ArrayList<>();
        LolLeague lolLeague = new LolLeague();
        Faker faker	= new Faker();

        for (int i = 1; i <=2 ; i++) {
            if (i == 1) {
                 lolLeague = lolLeagueRepository.findOneByName("EU West");
            } else{
                lolLeague = lolLeagueRepository.findOneByName("EU East & Nordic");
            }
            for (int j = 1; j <=10 ; j++) {
            LolTeam lolTeam = new LolTeam();
            lolTeam.setLolLeague(lolLeague);
            lolTeam.setTeam(faker.esports().team());
            list.add(lolTeam);
            }
        }

        lolTeamRepository.saveAll(list);
    }

    /**
     * Method is looking for all lol teams
     * @return list of lol teams
     */
    public List<LolTeam> allTeams(){
        return lolTeamRepository.findAll();
    }

    /**
     * Method is looking for teams by league id and set them in order by position
     * @param leagueId Id of league
     * @return list of teams by league in order
     */
    public List<LolTeam> findTeamsByLeagueId(Long leagueId){
        return lolTeamRepository.findAllByLolLeagueIdOrderByPosition(leagueId);
    }

    /**
     * Method is looking for specific team by team id
     * @param teamID team id
     * @return team
     */
    public LolTeam findTeamById(Long teamID){
        LolTeam team = lolTeamRepository.getOne(teamID);
        return team;
    }
}
