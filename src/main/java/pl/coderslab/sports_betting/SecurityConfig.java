package pl.coderslab.sports_betting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index").authenticated()
                .antMatchers("/api/*").permitAll()
                .antMatchers("/football/*").authenticated()
                .antMatchers("/resources/*").permitAll().anyRequest().permitAll()
                .antMatchers("/login", "/register", "/logout").permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                // now for easier prototyping
                .antMatchers("/*").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                  .and()
//                .logout()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
//                .logoutSuccessUrl("/login");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login", "/register").permitAll() // anonym can login or register
//                .antMatchers("/").access("hasRole('USER')") // home page is not allowed if not user is logged in
//                .and().formLogin().loginPage("/login")
//                .and()
//                .logout().logoutSuccessUrl("/register");
//
//        http.csrf().disable();
//    }