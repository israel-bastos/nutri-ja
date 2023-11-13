package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.domain.mail.Email;
import br.com.grupo.nutrija.application.service.CustomerService;
import br.com.grupo.nutrija.application.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class AnalizeCustomerController {

    private final CustomerService service;

    private final EmailService emailService;

    @Autowired
    public AnalizeCustomerController(CustomerService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @GetMapping("/analyze")
    public ModelAndView start(){
        List<Customer> list = this.service.analizeCustomer();

        if (list.isEmpty()) {
            return new ModelAndView("customer/analyze")
                    .addObject("customers", list)
                    .addObject("message", "Nenhum cliente em estado de alerta");
        }

        return new ModelAndView("customer/analyze")
                .addObject("customers", list);
    }
}
