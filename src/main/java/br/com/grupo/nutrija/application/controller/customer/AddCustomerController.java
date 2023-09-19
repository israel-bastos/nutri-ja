package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.domain.professional.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.CustomerService;
import br.com.grupo.nutrija.application.service.NutritionistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class AddCustomerController {

    private final CustomerService service;
    private final NutritionistService nutritionistService;

    private static final Logger logger = LoggerFactory.getLogger(AddCustomerController.class);

    @Autowired
    public AddCustomerController(CustomerService service, NutritionistService nutritionistService) {
        this.service = service;
        this.nutritionistService = nutritionistService;
    }

    @GetMapping("/record")
    public ModelAndView register(){

        return new ModelAndView("customer/register")
                .addObject("customer", new Customer());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer){

        String username = SecurityContextHolder.getContext().getAuthentication()
                .getName();

        Optional<Nutritionist> nutritionist = nutritionistService.findByUsername(username);
        if (nutritionist.isEmpty()) throw new RuntimeException("Problema ao recuperar usuário logado");

        customer.setNutritionist(nutritionist.get());

        this.service.save(customer);
        logger.info(" customer saved {}", customer.getId());

        return redirectToHome();
    }

    public String redirectToHome(){
        return "redirect:/";
    }
}
