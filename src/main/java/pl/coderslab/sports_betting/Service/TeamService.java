package pl.coderslab.sports_betting.Service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.League;
import pl.coderslab.sports_betting.Entity.Team;
import pl.coderslab.sports_betting.Repository.LeagueRepository;
import pl.coderslab.sports_betting.Repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamService {

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    TeamRepository teamRepository;

    public void populateDb(){
        List<Team> list = new ArrayList<>();
        League league = new League();
        Faker faker	=	new Faker();

        for (int i = 1; i <=2 ; i++) {
            if (i == 1) {
                 league = leagueRepository.findOneByName("Somewhership");
            } else{
                league = leagueRepository.findOneByName("Randomship");
            }
            for (int j = 0; j <=10 ; j++) {
            Team team = new Team();
            team.setTeam("aaa");
            team.setLeague(league);
            team.setTeam("FC " + faker.lordOfTheRings().location());

            list.add(team);
            }
        }

        teamRepository.saveAll(list);
    }

    public List<Team> allTeams(){
        return teamRepository.findAll();
    }

}
