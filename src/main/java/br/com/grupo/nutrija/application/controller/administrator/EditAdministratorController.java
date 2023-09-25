package br.com.grupo.nutrija.application.controller.administrator;

import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admins/edit")
public class EditAdministratorController {

    private final AdministratorService service;

    @Autowired
    public EditAdministratorController(AdministratorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        Administrator byId = this.service.getById(id);

        return new ModelAndView("admin/edit")
                .addObject("administrator", byId);
    }

    @PostMapping("/edited")
    public String edit(Administrator administrator){
        this.service.save(administrator);

        return redirectAllAdministrators();
    }

    public String redirectAllAdministrators(){
        return "redirect:/admins/list/all";
    }
}
