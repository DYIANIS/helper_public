package com.shtura.helper.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shtura.helper.repositories.UserRepository;

@Controller
public class WelcomeController{
    
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/welcome")
    public String login() {
        System.out.println("--------------------- WELCOM TO WELCOM ---------------------");
        return "welcome";
    }
    
    @GetMapping("/welcome")
    public String message(Model model) {
        
        model.addAttribute("adminEmail", userRepository.findByLogin("admin").getEmail());
        return "welcome";
    }
}
