package com.shtura.helper.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PoController {

    @RequestMapping("/po")
    public String admin() {
        System.out.println("--------------------- WELCOM TO PO ---------------------");
        return "po";
    }
}
