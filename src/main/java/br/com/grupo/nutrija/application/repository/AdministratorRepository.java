package br.com.grupo.nutrija.application.repository;

import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findByUsername(String username);

    Administrator findByFullName(String fullName);
}
