package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers/delete")
public class RemoveCustomerController {

    private final CustomerService service;

    private static final Logger logger = LoggerFactory.getLogger(RemoveCustomerController.class);

    @Autowired
    public RemoveCustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") long id){
        Customer byId = this.service.getById(id);
        this.service.deleteById(byId.getId());

        return redirectAllCustomers();

    }

    public String redirectAllCustomers(){
        return "redirect:/customers/list/all";
    }
}
