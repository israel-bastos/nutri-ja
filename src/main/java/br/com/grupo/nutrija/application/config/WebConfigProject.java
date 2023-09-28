package br.com.grupo.nutrija.application.config;

import br.com.grupo.nutrija.application.service.AdministratorUserDetailsService;
import br.com.grupo.nutrija.application.service.NutritionistUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigProject extends WebSecurityConfigurerAdapter{

    private final AdministratorUserDetailsService administratorUserDetailsService;

    private final NutritionistUserDetailsService nutritionistUserDetailsService;

    @Autowired
    WebConfigProject(AdministratorUserDetailsService administratorUserDetailsService, NutritionistUserDetailsService nutritionistService){
        this.administratorUserDetailsService = administratorUserDetailsService;
        this.nutritionistUserDetailsService = nutritionistService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
        .antMatchers("/img/**").permitAll()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/fonts/**").permitAll()
        .antMatchers("/vendors/**").permitAll()
        .anyRequest().authenticated();

        http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll();

        http.logout()
        .logoutRequestMatcher(
            new AntPathRequestMatcher("/logout", "GET")
        )
        .logoutSuccessUrl("/login");

        http.rememberMe()
                .tokenValiditySeconds(21600)
                .key("remember-key");
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(administratorUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());

        auth.userDetailsService(nutritionistUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
}
