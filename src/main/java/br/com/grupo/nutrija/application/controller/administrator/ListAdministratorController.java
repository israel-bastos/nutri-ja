
package br.com.grupo.nutrija.application.controller.administrator;

import br.com.grupo.nutrija.application.domain.administrator.Administrator;
import br.com.grupo.nutrija.application.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admins/list")
public class ListAdministratorController {

    private final AdministratorService service;

    @Autowired
    public ListAdministratorController(AdministratorService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ModelAndView all() {
        List<Administrator> all = this.service.findAll();

        return new ModelAndView("admin/all")
                .addObject("administrators", all);
    }
}
