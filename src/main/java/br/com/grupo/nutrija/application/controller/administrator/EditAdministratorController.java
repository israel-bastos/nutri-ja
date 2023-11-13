package br.com.grupo.nutrija.application.controller.administrator;

import br.com.grupo.nutrija.application.config.SecurityConfig;
import br.com.grupo.nutrija.application.domain.Password;
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

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/admins/edit")
public class EditAdministratorController {

    private static final Logger logger = LoggerFactory.getLogger(EditAdministratorController.class);

    private final AdministratorService service;

    @Autowired
    public EditAdministratorController(AdministratorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        Administrator byId = this.service.getById(id);

        return new ModelAndView("admin/edit")
                .addObject("administrator", byId);
    }

    @PostMapping("/edited")
    public String edit(@ModelAttribute Administrator administrator, @RequestParam("file") MultipartFile imageFile){
        try {
            if(UploadImageUtil.doUploadImage(imageFile) && !imageFile.isEmpty()){
                administrator.setDisplayImage(imageFile.getOriginalFilename());
            } else {
                administrator.setDisplayImage(administrator.getDisplayImage());
            }

            this.service.save(administrator);

            logger.info("success saved! {} ", administrator.getFullName());

            return redirectAllAdministrators();

            //TODO - later customize exception
        } catch (RuntimeException re){
            new ModelAndView().addObject("msgError", re.getMessage());
            logger.error(re.getMessage());

            return redirectAllAdministrators();
        }
    }

    @GetMapping("/password")
    public ModelAndView editPassword(Principal principal) {
        Optional<Administrator> administrator = this.service.findByUsername(principal.getName());

        if (administrator.isPresent()){
            Password password = new Password(administrator.get().getPassword());
            return new ModelAndView("admin/edit-password")
                    .addObject("currentPassword", password)
                    .addObject("password", new Password());
        }

        throw new NoSuchElementException("Usuário não encontrado");
    }

    @PostMapping("/password/edited")
    public String editPassword(Principal principal, Password password) {
        Optional<Administrator> administrator = this.service.findByUsername(principal.getName());

        if (SecurityConfig.passwordMatcher(password.getActualPassword(), administrator.get().getPassword())){
            administrator.get().setPassword(SecurityConfig.encoder(password.getNewPassword()));
            this.service.save(administrator.get());
        }

        return redirectToHome();

    }

    private String redirectAllAdministrators(){
        return "redirect:/admins/list/all";
    }

    private String redirectToHome(){return "redirect:/";}

}
