package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.professional.entity.Nutritionist;
import br.com.grupo.nutrija.application.domain.user.entity.util.UploadImageUtil;
import br.com.grupo.nutrija.application.service.NutritionistService;
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
//                .addObject("perfil", Perfils.value())
                .addObject("nutritionist", new Nutritionist());
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute Nutritionist nutritionist, @RequestParam("file") MultipartFile imageFile){
        String hashPassword = SecurityConfig.encoder(nutritionist.getPassword());
        nutritionist.setPassword(hashPassword);

        ModelAndView mv = new ModelAndView("nutritionist/register")
                .addObject("nutritionist", nutritionist);

        try {

            if(UploadImageUtil.doUploadImage(imageFile)){
                nutritionist.setImageFile(imageFile.getOriginalFilename());
            }

            this.service.save(nutritionist);

            logger.info("success saved! {} ", nutritionist.getName());

            return home();

            //TODO - later customize exception
        } catch (RuntimeException re){
            mv.addObject("msgError", re.getMessage());

            logger.error(re.getMessage());

            return mv;
        }
    }

    private ModelAndView home(){
        return new ModelAndView("/home/index");
    }
}
