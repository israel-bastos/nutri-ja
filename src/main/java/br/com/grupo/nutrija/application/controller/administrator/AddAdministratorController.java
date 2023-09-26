package br.com.grupo.nutrija.application.controller.administrator;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.service.AdministratorService;
import br.com.grupo.nutrija.application.util.UploadImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public String register(@ModelAttribute Administrator administrator, @RequestParam("file") MultipartFile imageFile){
        String hashPassword = SecurityConfig.encoder(administrator.getPassword());
        administrator.setPassword(hashPassword);

        try {

            if(UploadImageUtil.doUploadImage(imageFile)){
                administrator.setDisplayImage(imageFile.getOriginalFilename());
            }

            this.service.save(administrator);

            logger.info("success saved! {} ", administrator.getFullName());

            return redirectToHome();

            //TODO - later customize exception
        } catch (RuntimeException re){
            new ModelAndView().addObject("msgError", re.getMessage());
            logger.error(re.getMessage());

            return redirectToHome();
        }
    }

    public String redirectToHome(){
        return "redirect:/";
    }
}
