package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//    @GetMapping("")
//    public String showHomePage() {
//        return "redirect:/books";
//    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login/login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "login/register";
    }
}
