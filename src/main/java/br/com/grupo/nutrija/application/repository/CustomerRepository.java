package br.com.grupo.nutrija.application.repository;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from customer limit 5", nativeQuery = true)
    List<Customer> findAllFirst5RecentCustomers();
}
