package com.shtura.helper.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shtura.helper.entity.Role;
import com.shtura.helper.entity.UserInfo;
import com.shtura.helper.parser.Parser;
import com.shtura.helper.service.UserInfoService;
import com.shtura.helper.service.security.UsernameExistsException;
import com.shtura.helper.web.dto.UserInfoDto;

@Controller
public class InfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/info")
    public String login(Model model) {
        model.addAttribute("userInfo", new UserInfoDto());
        return "info";
    }

    @PostMapping("/info")
    public ModelAndView registerNewAccount(@ModelAttribute("userInfo") @Valid UserInfoDto userInfoDto,
            BindingResult result, Principal user) {
        UserInfo userInfoTest = null;
        if (!result.hasErrors()) {
            userInfoTest = createUserInfo(userInfoDto, user.getName());
            if (userInfoTest == null) {
                result.rejectValue("username", "error.user.exists");
            }
        }
        if (result.hasErrors()) {
            return new ModelAndView("info", "userInfo", userInfoDto);
        }
        else {
            if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
                return new ModelAndView("redirect:/admin");
            }
            if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.IT)) {
                return new ModelAndView("redirect:/connect");
            }
            if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.PO)) {
                return new ModelAndView("redirect:/connect");
            }
            if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.DEFAULT)) {
                return new ModelAndView("redirect:/welcome");
            }

            System.out.println("!!!!!!! You have problems ! ;) infoController.java line 56 !!!!!!!"); 
            return new ModelAndView("info", "userInfo", userInfoDto); // default
        }
    }

    private UserInfo createUserInfo(UserInfoDto userInfoDto, String name) {
        UserInfo userInfoTest = null;
        try {
            userInfoTest = userInfoService.inputUserInfo(userInfoDto, name);
        } catch (UsernameExistsException e) {
            return null;
        }
        return userInfoTest;
    }
}
