package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.domain.professional.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.NutritionistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nutritionists/edit")
public class EditNutritionistController {

    private final NutritionistService service;

    private static final Logger logger = LoggerFactory.getLogger(EditNutritionistController.class);

    @Autowired
    public EditNutritionistController(NutritionistService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        Nutritionist byId = this.service.getById(id);

        return new ModelAndView("nutritionist/edit")
                .addObject("nutritionist", byId);
    }

    @PostMapping("/edited")
    public String edit(Nutritionist nutritionist){
        this.service.save(nutritionist);

        return redirectAllNutritionists();
    }

    public String redirectAllNutritionists(){
        return "redirect:/nutritionists/list/all";
    }
}
