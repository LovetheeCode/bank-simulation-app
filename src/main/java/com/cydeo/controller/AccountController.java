package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    /*
    write a method to return index.html ancluding account list info
    endpoint:index
     */
    @GetMapping("/index")
    public String getIndexPage(Model model){

        return "account/index";

    }
}
