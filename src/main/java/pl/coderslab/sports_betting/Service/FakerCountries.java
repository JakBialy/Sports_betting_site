//package pl.coderslab.sports_betting.Rest;
//
//import com.github.javafaker.Faker;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class FakerCountries {
//    private ArrayList<JSONObject> countries	=	new	ArrayList<>();
//
//    FakerCountries()	throws JSONException {
//        this.regenerate();
//    }
//
//    public	ArrayList<JSONObject>	getCountries()	{
//        return	countries;
//    }
//
//
//    @Scheduled(fixedRate = 5000)
//    public	void regenerate() throws JSONException {
//        Faker faker	=	new Faker();
//        countries.clear();
//
//        for	(int i = 0;	i < 10; i++) {
//            JSONObject	oJsonInner	=	new	JSONObject();
//            oJsonInner.put("address", faker.address().country());
//            countries.add(oJsonInner);
//        }
//    }
//}
