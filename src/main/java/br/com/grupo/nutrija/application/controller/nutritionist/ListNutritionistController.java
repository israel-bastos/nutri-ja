
package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/nutritionists/list")
public class ListNutritionistController {

    private final NutritionistService service;

    @Autowired
    public ListNutritionistController(NutritionistService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ModelAndView all() {
        List<Nutritionist> all = this.service.findAll();

        return new ModelAndView("nutritionist/all")
                .addObject("nutritionists", all);
    }
}
