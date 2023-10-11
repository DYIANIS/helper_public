package com.shtura.helper.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ItController {

    @RequestMapping("/it")
    public String admin() {
        System.out.println("--------------------- WELCOM TO IT ---------------------");
        return "it";
    }
}
