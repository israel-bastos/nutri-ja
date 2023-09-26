package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    private final AdministratorRepository repository;

    @Autowired
    AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public void save(Administrator administrator){
        this.repository.save(administrator);
    }

    public List<Administrator> findAll() {
        return this.repository.findAll();
    }

    public Administrator getById(long id){return this.repository.getById(id);}

    public void deleteById(long id){
        this.repository.deleteById(id);
    }

    public Optional<Administrator> findByUsername(String username){return this.repository.findByUsername(username);}
}
