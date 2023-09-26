package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.config.SecurityConfig;
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
    public String register(@ModelAttribute Nutritionist nutritionist, @RequestParam("file") MultipartFile imageFile){
        String hashPassword = SecurityConfig.encoder(nutritionist.getPassword());
        nutritionist.setPassword(hashPassword);

        try {

            if(UploadImageUtil.doUploadImage(imageFile)){
                nutritionist.setDisplayImage(imageFile.getOriginalFilename());
            }

            this.service.save(nutritionist);

            logger.info("success saved! {} ", nutritionist.getFullName());

            return redirectToHome();

            //TODO - later customize exception
        } catch (RuntimeException re){
            new ModelAndView().addObject("msgError", re.getMessage());
            logger.error(re.getMessage());
        }

        return redirectToHome();
    }

    public String redirectToHome(){
        return "redirect:/";
    }
}
