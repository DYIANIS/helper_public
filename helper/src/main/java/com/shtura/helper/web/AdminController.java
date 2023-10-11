package com.shtura.helper.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin() {
        System.out.println("--------------------- WELCOM TO ADMIN ---------------------");
        return "admin";
    }
}
