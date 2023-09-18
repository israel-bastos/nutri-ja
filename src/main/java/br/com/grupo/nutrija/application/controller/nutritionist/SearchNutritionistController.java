package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("nutritionists/search")
public class SearchNutritionistController {

    private final NutritionistService service;

    @Autowired
    SearchNutritionistController(NutritionistService service){
        this.service = service;
    }
}
