package br.com.grupo.nutrija.application.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig {

    SecurityConfig(){}

    public static String encoder(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.encode(password);
    }

    public static boolean passwordMatcher(String actualPassword, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(actualPassword, newPassword);
    }
}
