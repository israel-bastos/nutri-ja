
package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers/list")
public class ListCustomerController {

    private final CustomerService service;

    private static final Logger logger = LoggerFactory.getLogger(ListCustomerController.class);

    @Autowired
    public ListCustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ModelAndView all() {
        List<Customer> all = this.service.findAll();

        return new ModelAndView("customer/all")
                .addObject("customers", all);
    }

}
