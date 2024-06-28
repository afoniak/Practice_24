package com.example.shinydishes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home (Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String homepage (Model model) {
        return "home";
    }

}
