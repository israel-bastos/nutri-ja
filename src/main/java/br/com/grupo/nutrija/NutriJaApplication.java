package br.com.grupo.nutrija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NutriJaApplication {

    public static void main(String[] args) {

        SpringApplication.run(NutriJaApplication.class, args);
    }

}
