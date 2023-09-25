package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.Access;
import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectDBService {

    private final NutritionistRepository repository;

    @Autowired
    public InjectDBService(NutritionistRepository repository) {
        this.repository = repository;
    }

    public void injectDB(){
        Nutritionist admin = new Nutritionist(
                "admin",
                SecurityConfig.encoder("root"),
                "admin", Access.ADMIN);

         repository.save(admin);
    }
}
