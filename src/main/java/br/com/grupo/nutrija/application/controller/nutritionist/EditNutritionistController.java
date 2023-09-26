package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.controller.administrator.EditAdministratorController;
import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.NutritionistService;
import br.com.grupo.nutrija.application.util.UploadImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/nutritionists/edit")
public class EditNutritionistController {

    private static final Logger logger = LoggerFactory.getLogger(EditNutritionistController.class);

    private final NutritionistService service;

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
    public String edit(@ModelAttribute Nutritionist nutritionist, @RequestParam("file") MultipartFile imageFile){
        try {
            if(UploadImageUtil.doUploadImage(imageFile)){
                nutritionist.setDisplayImage(imageFile.getOriginalFilename());
            }

            this.service.save(nutritionist);

            logger.info("success saved! {} ", nutritionist.getFullName());

            return redirectAllNutritionists();

            //TODO - later customize exception
        } catch (RuntimeException re) {
            new ModelAndView().addObject("msgError", re.getMessage());
            logger.error(re.getMessage());

            return redirectAllNutritionists();
        }
    }

    private String redirectAllNutritionists(){
        return "redirect:/nutritionists/list/all";
    }
}
