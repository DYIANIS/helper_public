package com.shtura.helper.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
    
    private static String errorString = "";

    @RequestMapping("/myerror")
    public String login() {
        System.out.println("--------------------- WELCOM TO MYERROR ---------------------");

        return "myerror";
    }
    
    @ModelAttribute("Notes")
    public String getNotes() {
        return this.errorString;
    }

    @PostMapping("/goBack")
    public ModelAndView transference() {
        
        
        return new ModelAndView("redirect:/goHome");
    }

    public static String getErrorString() {
        return errorString;
    }

    public static void setErrorString(String errorString) {
        ErrorController.errorString = errorString;
    }
}
