package br.com.grupo.nutrija.application.controller.home;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/")
public class HomeController {

    private final CustomerService service;

    @Autowired
    HomeController(CustomerService service){
        this.service = service;
    }

    @GetMapping
    public ModelAndView home(){
        List<Customer> first5RecentCustomers = findFirst5RecentCustomers();

        return new ModelAndView("home/index")
                .addObject("recentCustomers", first5RecentCustomers);
    }

    private List<Customer> findFirst5RecentCustomers(){
        return this.service.findAllLast5RecentCustomers();
    }
}
