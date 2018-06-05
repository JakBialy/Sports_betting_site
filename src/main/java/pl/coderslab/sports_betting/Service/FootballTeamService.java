package pl.coderslab.sports_betting.Service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.FootballLeague;
import pl.coderslab.sports_betting.Entity.FootballTeam;
import pl.coderslab.sports_betting.Repository.FootballLeagueRepository;
import pl.coderslab.sports_betting.Repository.FootballTeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FootballTeamService {

    @Autowired
    FootballLeagueRepository footballLeagueRepository;

    @Autowired
    FootballTeamRepository footballTeamRepository;

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
            footballTeam.setTeam("aaa");
            footballTeam.setFootballLeague(footballLeague);
            footballTeam.setTeam("FC " + faker.lordOfTheRings().location());

            list.add(footballTeam);
            }
        }

        footballTeamRepository.saveAll(list);
    }

    public List<FootballTeam> allTeams(){
        return footballTeamRepository.findAll();
    }

    public List<FootballTeam> findTeamsByLeagueId(Long leagueId){
        return footballTeamRepository.findAllByFootballLeagueIdOrderByPosition(leagueId);
    }

    public FootballTeam findTeamById(Long teamID){
        Optional<FootballTeam> team = footballTeamRepository.findById(teamID);
        FootballTeam footballTeam1 = new FootballTeam();
        if (team.isPresent()) {
            footballTeam1 = team.get();
        }
        return footballTeam1;
    }
}
