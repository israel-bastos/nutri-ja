package br.com.grupo.nutrija.application.controller.administrator;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.AdministratorService;
import br.com.grupo.nutrija.application.service.NutritionistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admins")
public class AddAdministratorController {

    private final AdministratorService service;

    private static final Logger logger = LoggerFactory.getLogger(AddAdministratorController.class);

    @Autowired
    public AddAdministratorController(AdministratorService service) {
        this.service = service;
    }

    @GetMapping("/record")
    public ModelAndView register(){

        return new ModelAndView("admin/register")
                .addObject("administrator", new Administrator());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Administrator administrator){
        String hashPassword = SecurityConfig.encoder(administrator.getPassword());
        administrator.setPassword(hashPassword);

        this.service.save(administrator);
        logger.info("saved administrator {}", administrator.getId());

        return redirectToHome();
    }

    public String redirectToHome(){
        return "redirect:/";
    }
}
