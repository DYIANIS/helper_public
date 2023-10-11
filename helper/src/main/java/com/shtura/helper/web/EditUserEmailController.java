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
import com.shtura.helper.web.dto.EditUserEmailDto;

@Controller
public class EditUserEmailController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/editUserEmail")
    public String login() {
        System.out.println("--------------------- WELCOM TO editAdminEmail ---------------------");
        return "editUserEmail";
    }
    
    @GetMapping("/editUserEmail")
    public String reloadEditAdminEmail(Model model) {
        model.addAttribute("editUserEmailDto", new EditUserEmailDto());
        return "editUserEmail";
    }
    
    @ModelAttribute("getUsers")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/editUserEmail")
    public ModelAndView editUserEmail(@ModelAttribute("editUserEmailDto") @Valid EditUserEmailDto editUserEmailDto, BindingResult result, Principal user) {
        
        if (result.hasErrors()) {
            return new ModelAndView("editUserEmail", "connectDto", editUserEmailDto);
        }
        
        /*
         *          For DEMONSTRATION
         * */
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEMONSTRATION)) {
            return new ModelAndView("redirect:/editUserEmail");
        }
 
        User selectedUser = userRepository.findByLogin(editUserEmailDto.getSelectedLogin());
        
        selectedUser.setEmail(editUserEmailDto.getInputedUserEmail());
        
        userRepository.save(selectedUser);
        
        return new ModelAndView("redirect:/admin");
    }
}
