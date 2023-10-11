package com.shtura.helper.web;

import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shtura.helper.entity.Role;
import com.shtura.helper.service.UserInfoService;

@Controller
public class GoHomeController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/goHome")
    public ModelAndView login(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
            return new ModelAndView("redirect:/admin");
        }
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.IT)) {
            return new ModelAndView("redirect:/it");
        }
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.PO)) {
            return new ModelAndView("redirect:/po");
        }
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEFAULT)) {
            return new ModelAndView("redirect:/welcome");
        }
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/demonstration");
        }

        System.out.println("!!!!!!! You have problems ! ;) GoHomeController.java line 45 !!!!!!!");
        
        System.out.println("--------------------- GoHomeController ---------------------");
        System.out.println("--------------------- NOT_FOUNTED_USER_ROLE ---------------------");
        System.out.println("------------- NOT_FOUND_USER_HOME_PAGE -------------");
        
        StringWriter errors = new StringWriter();
        
        errors.write("GoHomeController" + System.lineSeparator());
        errors.write("NOT_FOUNTED_USER_ROLE" + System.lineSeparator());
        errors.write("NOT_FOUND_USER_HOME_PAGE" + System.lineSeparator());
        errors.write("----------------------------------------" + System.lineSeparator());
        errors.write("Сomments: check these classes:" + System.lineSeparator());
        errors.write("WebSecurityConfig.java" + System.lineSeparator());
        errors.write("GoHomeController.java" + System.lineSeparator());
        
        ErrorController.setErrorString(errors.toString());
        
        return new ModelAndView("redirect:/myerror");
    }

}
