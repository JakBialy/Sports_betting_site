package pl.coderslab.sports_betting.Service.Lol;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;
import pl.coderslab.sports_betting.Repository.Lol.LolLeagueRepository;
import pl.coderslab.sports_betting.Repository.Lol.LolTeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LolTeamService {

    @Autowired
    LolLeagueRepository lolLeagueRepository;

    @Autowired
    LolTeamRepository lolTeamRepository;

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
            lolTeam.setTeam(faker.witcher().character());
            list.add(lolTeam);
            }
        }

        lolTeamRepository.saveAll(list);
    }

    public List<LolTeam> allTeams(){
        return lolTeamRepository.findAll();
    }

    public List<LolTeam> findTeamsByLeagueId(Long leagueId){
        return lolTeamRepository.findAllByLolLeagueIdOrderByPosition(leagueId);
    }

    public LolTeam findTeamById(Long teamID){
        LolTeam team = lolTeamRepository.getOne(teamID);
        return team;
    }
}
