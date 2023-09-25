package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
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
@RequestMapping("/nutritionists")
public class AddNutritionistController {

    private final NutritionistService service;

    private static final Logger logger = LoggerFactory.getLogger(AddNutritionistController.class);

    @Autowired
    public AddNutritionistController(NutritionistService service) {
        this.service = service;
    }

    @GetMapping("/record")
    public ModelAndView register(){

        return new ModelAndView("nutritionist/register")
                .addObject("nutritionist", new Nutritionist());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Nutritionist nutritionist){
        String hashPassword = SecurityConfig.encoder(nutritionist.getPassword());
        nutritionist.setPassword(hashPassword);

        this.service.save(nutritionist);
        logger.info("saved nutritionist {}", nutritionist.getId());

        return redirectToHome();
    }

    public String redirectToHome(){
        return "redirect:/";
    }
}
