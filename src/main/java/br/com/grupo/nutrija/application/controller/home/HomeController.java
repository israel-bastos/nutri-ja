package br.com.grupo.nutrija.application.controller.home;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;
import br.com.grupo.nutrija.application.service.CustomerService;
import br.com.grupo.nutrija.application.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/")
public class HomeController {

    private final CustomerService customerService;

    private final NutritionistService nutritionistService;

    @Autowired
    HomeController(CustomerService customerService, NutritionistService nutritionistService){
        this.customerService = customerService;
        this.nutritionistService = nutritionistService;
    }

    @GetMapping
    public ModelAndView home(){
        List<Customer> first5RecentCustomers = findFirst5RecentCustomers();
        List<Nutritionist> first5RecentNutritionists = first5RecentNutritionists();

        ModelAndView modelAndView = new ModelAndView("home/index");

        modelAndView.addAllObjects(new ModelMap("recentCustomers", first5RecentCustomers));
        modelAndView.addAllObjects(new ModelMap("recentNutritionists", first5RecentNutritionists));

        return modelAndView;
    }

    private List<Customer> findFirst5RecentCustomers(){
        return this.customerService.findAllLast5RecentCustomers();
    }

    private List<Nutritionist> first5RecentNutritionists(){return this.nutritionistService.findAllLast5RecentNutritionists();}
}
