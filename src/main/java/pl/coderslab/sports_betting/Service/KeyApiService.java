package pl.coderslab.sports_betting.Service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.KeyApi;
import pl.coderslab.sports_betting.Repository.ApiRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

@Service
public class KeyApiService {
    @Autowired
    ApiRepository apiRepository;
    @Autowired
    UserRepository userRepository;

    public String giveRandomKey(){
        String generatedString = RandomStringUtils.randomAlphanumeric(20, 40);
        KeyApi api = new KeyApi();
        api.setCode(generatedString);
        api.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        apiRepository.save(api);
        return generatedString;
    }

    public boolean checkKey(String key){
         Boolean check = false;
        if (apiRepository.findOneByCode(key) != null) {
            check = true;
        }
        return check;
    }
}
