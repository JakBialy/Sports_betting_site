package pl.coderslab.sports_betting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SportsBettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsBettingApplication.class, args);
    }
}
