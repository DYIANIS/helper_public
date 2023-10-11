package com.shtura.helper.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemonstrationController {

    @RequestMapping("/demonstration")
    public String admin() {
        System.out.println("--------------------- WELCOM TO DemonstrationController ---------------------");
        return "demonstration";
    }
}
