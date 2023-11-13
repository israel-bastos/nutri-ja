package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/customers/edit")
public class EditCustomerController {

    private final CustomerService service;

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
    public String edit(@ModelAttribute Customer customer){
        double imc = this.service.calculateIMC(customer.getWeight(), customer.getHeight());
        BigDecimal roundImc = new BigDecimal(imc).setScale(2, BigDecimal.ROUND_HALF_UP);
        customer.setImc(roundImc.intValue());

        this.service.save(customer);

        return redirectAllCustomers();
    }

    public String redirectAllCustomers(){
        return "redirect:/customers/list/all";
    }
}
