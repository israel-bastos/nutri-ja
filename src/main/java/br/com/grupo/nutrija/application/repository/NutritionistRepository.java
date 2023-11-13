package br.com.grupo.nutrija.application.repository;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {

    Optional<Nutritionist> findByUsername(String username);

    @Query(value = "select * from nutritionist limit 5", nativeQuery = true)
    List<Nutritionist> findAllFirst5RecentNutritionists();
}
