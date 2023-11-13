package br.com.grupo.nutrija.application.controller.nutritionist;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.controller.administrator.EditAdministratorController;
import br.com.grupo.nutrija.application.domain.Password;
import br.com.grupo.nutrija.application.domain.administrator.Administrator;
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

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ModelAndView edit(@ModelAttribute Nutritionist nutritionist, @RequestParam("file") MultipartFile imageFile){
        try {
            if(UploadImageUtil.doUploadImage(imageFile) && !imageFile.isEmpty()){
                nutritionist.setDisplayImage(imageFile.getOriginalFilename());
            } else {
                nutritionist.setDisplayImage(nutritionist.getDisplayImage());
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

    @GetMapping("/password")
    public ModelAndView editPassword(Principal principal) {
        Optional<Nutritionist> nutritionist = this.service.findByUsername(principal.getName());

        if (nutritionist.isPresent()){
            Password password = new Password(nutritionist.get().getPassword());
            return new ModelAndView("nutritionist/edit-password")
                    .addObject("currentPassword", password)
                    .addObject("password", new Password());
        }

        throw new NoSuchElementException("Nutricionista não encontrado");
    }

    @PostMapping("/password/edited")
    public String editPassword(Principal principal, Password password) {
        Optional<Nutritionist> nutritionist = this.service.findByUsername(principal.getName());

        if (SecurityConfig.passwordMatcher(password.getActualPassword(), nutritionist.get().getPassword())){
            nutritionist.get().setPassword(SecurityConfig.encoder(password.getNewPassword()));
            this.service.save(nutritionist.get());
        }

        return redirectToHome();

    }

    private ModelAndView redirectAllNutritionists(){
        return new ModelAndView("nutritionist/all")
                .addObject("nutritionists", this.service.findAll());
    }

    private String redirectToHome(){return "redirect:/";}
}
