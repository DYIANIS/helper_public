package com.shtura.helper.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shtura.helper.entity.User;
import com.shtura.helper.service.security.UserRegistrationService;
import com.shtura.helper.service.security.UsernameExistsException;
import com.shtura.helper.web.dto.UserDto;

@Controller
public class RegistrationController {
    @Autowired
    DaoAuthenticationProvider authenticationManager;
    
    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerNewAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result) {
        User registered = null;
        if (!result.hasErrors()) {
            registered = createUserAccount(userDto);
            if (registered == null) {
                result.rejectValue("username", "error.user.exists");
            }
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", userDto);
        } else {
            Authentication request = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
            Authentication res = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(res);
            return new ModelAndView("redirect:/info");
        }
    }

    private User createUserAccount(UserDto userDto) {
        User registered = null;
        try {
            registered = userRegistrationService.registerNewUser(userDto);
        } catch (UsernameExistsException e) {
            return null;
        }
        return registered;
    }
}
