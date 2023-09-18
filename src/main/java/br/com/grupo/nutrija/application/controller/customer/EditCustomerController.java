package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers/edit")
public class EditCustomerController {

    private final CustomerService service;

    private static final Logger logger = LoggerFactory.getLogger(EditCustomerController.class);

    @Autowired
    public EditCustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        Customer byId = this.service.getById(id);

        return new ModelAndView("customer/edit")
                .addObject("customer", byId);
    }

    @PostMapping("/edited")
    public String edit(Customer customer){
        this.service.save(customer);

        return redirectAllCustomers();
    }

    public String redirectAllCustomers(){
        return "redirect:/customers/list/all";
    }
}
