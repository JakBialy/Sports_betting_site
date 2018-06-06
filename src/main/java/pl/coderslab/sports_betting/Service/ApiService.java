//package pl.coderslab.sports_betting.Service;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import pl.coderslab.sports_betting.Entity.ApiKey;
//import pl.coderslab.sports_betting.Repository.ApiRepository;
//
//@Service
//public class ApiService {
//    @Autowired
//    ApiRepository apiRepository;
//
//    public String giveRandomKey(){
//        String generatedString = RandomStringUtils.randomAlphanumeric(20, 40);
//        ApiKey api = new ApiKey();
//        api.setApiKey(generatedString);
//        apiRepository.save(api);
//        return generatedString;
//    }
//
//    public boolean checkKey(String key){
//        Boolean check = false;
//        if (apiRepository.findOneByKey(key) != null) {
//            check = true;
//        }
//        return check;
//    }
//}
