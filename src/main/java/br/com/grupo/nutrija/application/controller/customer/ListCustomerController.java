
package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/customers/list")
public class ListCustomerController {

    private final CustomerService service;

    @Autowired
    public ListCustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ModelAndView start(@RequestParam(defaultValue = "1") int page){
        Pageable pageReq = PageRequest.of((page - 1),  5);
        Page<Customer> resultPage = this.service.findAllPageable(pageReq);

        return new ModelAndView("customer/all")
                .addObject("customers", resultPage);
    }
}
