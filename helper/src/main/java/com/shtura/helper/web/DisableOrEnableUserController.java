package com.shtura.helper.web;

import java.security.Principal;
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
import com.shtura.helper.web.dto.DisableOrEnableUserDto;

@Controller
public class DisableOrEnableUserController {
    
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping("/disableOrEnableUser")
    public String login() {
        System.out.println("--------------------- WELCOM TO disableOrEnableUser ---------------------");
        return "disableOrEnableUser";
    }

    
    @ModelAttribute("getUsers")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
    

    @GetMapping("/disableOrEnableUser")
    public String reloadAddRoleToUser(Model model) {
        model.addAttribute("disableOrEnableUserDto", new DisableOrEnableUserDto());
        return "disableOrEnableUser";
    }
    
    @PostMapping("/disableOrEnableUser")
    public ModelAndView disableOrEnableUserDto(@ModelAttribute("addRoleToUserDto") @Valid DisableOrEnableUserDto disableOrEnableUserDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("disableOrEnableUser", "disableOrEnableUserDto", disableOrEnableUserDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/disableOrEnableUser");
        }
        
        User selectedUser = userRepository.findByLogin(disableOrEnableUserDto.getSelectedLogin());
        
        selectedUser.setBanned(Boolean.valueOf(disableOrEnableUserDto.getSelectedBanned()));
        
        userRepository.save(selectedUser);
        
        return new ModelAndView("redirect:/admin");
    }
}
