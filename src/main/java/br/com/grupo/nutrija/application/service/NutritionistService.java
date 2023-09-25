package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Nutritionist> findByUsername(String username){return this.repository.findByUsername(username);}
}
