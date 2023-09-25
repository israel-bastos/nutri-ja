package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void save(Customer customer){
        this.repository.save(customer);
    }

    public Page<Customer> findAllPageable(Pageable page) {
        return this.repository.findAll(page);
    }

    public List<Customer> findAllLast5RecentCustomers() {
        return this.repository.findAllFirst5RecentCustomers();
    }

    public Customer getById(long id){
        return this.repository.getById(id);
    }

    public void deleteById(long id){
        this.repository.deleteById(id);
    }
}
