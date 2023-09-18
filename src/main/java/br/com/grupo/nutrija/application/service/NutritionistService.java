package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.professional.entity.Nutritionist;
import br.com.grupo.nutrija.application.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionistService {

    private final NutritionistRepository repository;

    @Autowired
    NutritionistService(NutritionistRepository repository) {
        this.repository = repository;
    }

    public void save(Nutritionist customer){
        this.repository.save(customer);
    }

    public List<Nutritionist> findAll() {
        return this.repository.findAll();
    }

    public Nutritionist getById(long id){
        return this.repository.getById(id);
    }

    public void deleteById(long id){
        this.repository.deleteById(id);
    }

    public Nutritionist findByName(String name){return this.repository.findByName(name);}
}
