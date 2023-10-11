package com.shtura.helper.web;

import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shtura.helper.entity.Role;
import com.shtura.helper.entity.User;
import com.shtura.helper.repositories.UserRepository;
import com.shtura.helper.web.dto.AddRoleToUserDto;
import com.shtura.helper.web.dto.ConnectDto;

@Controller
public class AddRoleToUserController {
    
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping("/addRoleToUser")
    public String login() {
        System.out.println("--------------------- WELCOM TO addRoleToUser ---------------------");
        return "addRoleToUser";
    }

    
    @ModelAttribute("getUsers")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    @ModelAttribute("getRoles")
    public Role[] getRoles() {
        return Role.values();
    }
    
    @GetMapping("/addRoleToUser")
    public String reloadAddRoleToUser(Model model) {
        model.addAttribute("addRoleToUserDto", new AddRoleToUserDto());
        return "addRoleToUser";
    }
    
    @PostMapping("/addRoleToUser")
    public ModelAndView addRoleToUser(@ModelAttribute("addRoleToUserDto") @Valid AddRoleToUserDto addRoleToUserDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("addRoleToUser", "addRoleToUserDto", addRoleToUserDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/addRoleToUser");
        }
        
        User selectedUser = userRepository.findByLogin(addRoleToUserDto.getSelectedLogin());
        
        selectedUser.setRoles(new ArrayList<>(Arrays.asList(Role.valueOf(addRoleToUserDto.getSelectedRole()))));
        
        userRepository.save(selectedUser);
        
        return new ModelAndView("redirect:/admin");
    }
}
