package com.shtura.helper.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shtura.helper.entity.Role;
import com.shtura.helper.entity.User;
import com.shtura.helper.parser.Parser;
import com.shtura.helper.repositories.UserRepository;

@Controller
public class LoginController {
    
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index(Locale locale, Model model, RedirectAttributes attrs) {
        model.addAttribute("locale", locale);
               
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            } else {
            username = principal.toString();
        }
        
        User loginedUser = userRepository.findByLogin(username);
        
        System.out.println("User logined" + loginedUser.toString());
        
        System.out.println("------------------------------>");
        //Parser.saveToFileFromDB();
        System.out.println("<------------------------------");
        
        if(loginedUser.getRoles().isEmpty()) {
            return "redirect:/welcome";
        }
        
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return "redirect:/demonstration";
        }
        
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEFAULT)) {
            return "redirect:/welcome";
        }
        
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
            return "redirect:/admin";
        }
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.IT)) {
            return "redirect:/it";
        }
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.PO)) {
            return "redirect:/po";
        }
        
        return null;
    }
}
