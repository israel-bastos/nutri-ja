package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectDBService {

    private final AdministratorRepository repository;

    @Autowired
    public InjectDBService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public void injectDB(){
        Administrator admin = new Administrator(
                "admin",
                SecurityConfig.encoder("root"),
                "admin", "admin.png");

         repository.save(admin);
    }
}
