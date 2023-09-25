package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.domain.nutritionist.entity.NutritionistUserDetailsImpl;
import br.com.grupo.nutrija.application.repository.NutritionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NutritionistUserDetailsService implements UserDetailsService{

    private final NutritionistRepository repository;

    @Autowired
    public NutritionistUserDetailsService(NutritionistRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Nutritionist nutritionist = repository.findByUsername(username)
        .orElseThrow( () -> new UsernameNotFoundException("Nutricionista não encontrado na base de dados"));
        return new NutritionistUserDetailsImpl(nutritionist);
    }
    
}
