package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.repository.CustomerRepository;
import br.com.grupo.nutrija.application.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionistService {

    private final NutritionistRepository repository;

    private final CustomerRepository customerRepository;

    @Autowired
    NutritionistService(NutritionistRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
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

    public List<Nutritionist> findAllLast5RecentNutritionists() {return this.repository.findAllFirst5RecentNutritionists();}
}
