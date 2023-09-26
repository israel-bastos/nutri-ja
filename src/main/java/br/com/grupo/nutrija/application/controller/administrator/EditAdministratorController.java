package br.com.grupo.nutrija.application.controller.administrator;

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
    public String edit(Administrator administrator, @RequestParam("file") MultipartFile imageFile){
        try {

            if(UploadImageUtil.doUploadImage(imageFile)){
                administrator.setDisplayImage(imageFile.getOriginalFilename());
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

    private String redirectAllAdministrators(){
        return "redirect:/admins/list/all";
    }

}
