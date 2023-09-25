package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.domain.administrator.AdministratorUserDetailsImpl;
import br.com.grupo.nutrija.application.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministratorUserDetailsService implements UserDetailsService{

    private final AdministratorRepository repository;

    @Autowired
    public AdministratorUserDetailsService(AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = repository.findByUsername(username)
        .orElseThrow( () -> new UsernameNotFoundException("Administrador não encontrado na base de dados"));
        return new AdministratorUserDetailsImpl(administrator);
    }
    
}
