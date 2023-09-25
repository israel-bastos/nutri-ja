package br.com.grupo.nutrija.application.controller.administrator;

import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins/delete")
public class RemoveAdministratorController {

    private final AdministratorService service;

    @Autowired
    RemoveAdministratorController(AdministratorService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") long id){
        Administrator byId = this.service.getById(id);
        this.service.deleteById(byId.getId());

        return redirectAllAdministrator();

    }

    public String redirectAllAdministrator(){
        return "redirect:/admins/list/all";
    }
}
