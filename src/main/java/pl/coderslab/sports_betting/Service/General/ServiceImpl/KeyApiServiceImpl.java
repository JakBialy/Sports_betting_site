package pl.coderslab.sports_betting.Service.General.ServiceImpl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.KeyApi;
import pl.coderslab.sports_betting.Repository.General.ApiRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.General.Service.KeyApiService;

@Service
public class KeyApiServiceImpl implements KeyApiService {
    private final ApiRepository apiRepository;
    private final UserRepository userRepository;

    @Autowired
    public KeyApiServiceImpl(ApiRepository apiRepository, UserRepository userRepository) {
        this.apiRepository = apiRepository;
        this.userRepository = userRepository;
    }

    /**
     * Method is generating random key for our API
     * generates random code from 20 to 40
     * code and actual user are set into api
     * api is saved
     * @return return string - our code which can be used to view it
     */
    public String giveRandomKey(){
        String generatedString = RandomStringUtils.randomAlphanumeric(20, 40);
        KeyApi api = new KeyApi();
        api.setCode(generatedString);
        api.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        apiRepository.save(api);
        return generatedString;
    }

    /**
     * Method is checking if our api key is in database
     * initial is set as false if api is find them set to true
     * @param key - api key
     * @return boolean check
     */
    public boolean checkKey(String key){
         boolean check = false;
        if (apiRepository.findOneByCode(key) != null) {
            check = true;
        }
        return check;
    }
}
