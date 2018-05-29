package pl.coderslab.sports_betting.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Service.FakerService;

@RestController
@RequestMapping("/api")
public	class	FakerResource	{

    // should be avaliable only for admin roles

    @Autowired
    FakerService fakerService;

//    @Autowired
//    FakerCountries fakerCountries;

//    @GetMapping(path= "/hello-world")
//    public	String	helloWorld()	{
//        return	"Hello	World";
//    }

    @GetMapping(path = "/fake-today-games")
    public	String	sample() {
        return	fakerService.getTodayGames().toString();
    }

//    @GetMapping(path = "/fake-countries")
//    public	String	countries() {
//        return fakerCountries.getCountries().toString();
//    }


}
