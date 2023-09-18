package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("/customers/search")
public class SearchCustomerController {

    private final CustomerService service;

    @Autowired
    SearchCustomerController(CustomerService service){
        this.service = service;
    }
}
