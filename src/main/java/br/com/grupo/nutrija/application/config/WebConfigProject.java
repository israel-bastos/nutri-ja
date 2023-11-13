package br.com.grupo.nutrija.application.config;

import br.com.grupo.nutrija.application.service.AdministratorUserDetailsService;
import br.com.grupo.nutrija.application.service.NutritionistUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
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

    private class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {

            response.sendRedirect("/login?error=true");
        }
    }
}
