package br.com.grupo.nutrija.application.repository;

import br.com.grupo.nutrija.application.domain.professional.entity.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {

    Optional<Nutritionist> findByUsername(String username);

    Nutritionist findByFullName(String fullName);
}
