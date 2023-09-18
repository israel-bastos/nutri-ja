package br.com.grupo.nutrija.application.controller.customer;

import br.com.grupo.nutrija.application.domain.customer.entity.Customer;
import br.com.grupo.nutrija.application.domain.professional.entity.Nutritionist;
import br.com.grupo.nutrija.application.domain.professional.entity.NutritionistUserDetailsImpl;
import br.com.grupo.nutrija.application.domain.user.entity.util.UploadImageUtil;
import br.com.grupo.nutrija.application.service.CustomerService;
import br.com.grupo.nutrija.application.service.NutritionistService;
import br.com.grupo.nutrija.application.service.NutritionistUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class AddCustomerController {

    private final CustomerService service;
    private final NutritionistService nutritionistService;

    private static final Logger logger = LoggerFactory.getLogger(AddCustomerController.class);

    @Autowired
    public AddCustomerController(CustomerService service, NutritionistService nutritionistService) {
        this.service = service;
        this.nutritionistService = nutritionistService;
    }

    @GetMapping("/record")
    public ModelAndView register(){

        return new ModelAndView("customer/register")
                .addObject("customer", new Customer());
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute Customer customer, @RequestParam("file")MultipartFile imageFile){

        String name = SecurityContextHolder.getContext().getAuthentication()
                .getName();

        Nutritionist nutritionist = nutritionistService.findByName(name);
        customer.setNutritionist(nutritionist);

        ModelAndView mv = new ModelAndView("customer/register")
                .addObject("customer", customer);

        try {

            if(UploadImageUtil.doUploadImage(imageFile)){
                customer.setImageFile(imageFile.getOriginalFilename());
            }

            this.service.save(customer);

            logger.info("success saved! {} ", customer.getName());

            return home();

        //TODO - later customize exception
        } catch (RuntimeException re){
            mv.addObject("msgError", re.getMessage());

            logger.error(re.getMessage());

            return mv;
        }
    }

    private ModelAndView home(){
        return new ModelAndView("/home/index");
    }
}
