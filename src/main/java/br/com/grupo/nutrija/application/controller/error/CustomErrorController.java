package br.com.grupo.nutrija.application.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{

    @RequestMapping("/error")
    public ModelAndView customError(HttpServletRequest request){

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());

        if(statusCode == HttpStatus.NOT_FOUND.value()){
           return new ModelAndView("/erros/error-404");

        }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
            return new ModelAndView("/erros/error-500");
        }

        return new ModelAndView("error");
    }
}
