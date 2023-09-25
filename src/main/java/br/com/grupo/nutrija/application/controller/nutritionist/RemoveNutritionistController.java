package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nutritionists/delete")
public class RemoveNutritionistController {

    private final NutritionistService service;

    @Autowired
    RemoveNutritionistController(NutritionistService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") long id){
        Nutritionist byId = this.service.getById(id);
        this.service.deleteById(byId.getId());

        return redirectAllNutritionist();

    }

    public String redirectAllNutritionist(){
        return "redirect:/nutritionists/list/all";
    }
}
