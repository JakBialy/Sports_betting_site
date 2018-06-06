package pl.coderslab.sports_betting.Service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.KeyApi;
import pl.coderslab.sports_betting.Repository.ApiRepository;

@Service
public class KeyApiService {
    @Autowired
    ApiRepository apiRepository;

    public String giveRandomKey(){
        String generatedString = RandomStringUtils.randomAlphanumeric(20, 40);
        KeyApi api = new KeyApi();
        api.setCode(generatedString);
        apiRepository.save(api);
        return generatedString;
    }

    public boolean checkKey(String key){
        Boolean check = true;
        if (apiRepository.findOneByCode(key) != null) {
            check = true;
        }
        return check;
    }
}
