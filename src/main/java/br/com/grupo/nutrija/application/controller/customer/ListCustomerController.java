
package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import br.com.grupo.nutrija.application.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers/list")
public class ListCustomerController {

    private final CustomerService customerService;

    @Autowired
    public ListCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ModelAndView start(){
        List<Customer> list = this.customerService.findAll();

        if (list.isEmpty()) {
            return new ModelAndView("customer/all")
                    .addObject("customers", list)
                    .addObject("message", "Nenhum cliente cadastrado");
        }

        return new ModelAndView("customer/all")
                .addObject("customers", list);
    }
}
