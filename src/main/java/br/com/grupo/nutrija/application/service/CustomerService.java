package br.com.grupo.nutrija.application.service;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Customer> findAll() {
        return this.repository.findAll();
    }

    public List<Customer> findAllLast5RecentCustomers() {
        return this.repository.findAllFirst5RecentCustomers();
    }

    public Customer getById(long id){ return this.repository.getById(id); }

    public void deleteById(long id){
        this.repository.deleteById(id);
    }

    public List<Customer> analizeCustomer(){return this.repository.analizeCustomer();}

    public double calculateIMC(int weight, double height) {
        return (weight / (height * height)*10000);
    }
}
