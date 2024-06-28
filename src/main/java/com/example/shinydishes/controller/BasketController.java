package com.example.shinydishes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;



@Controller
public class BasketController {

    @GetMapping("/basket")
    public String basket(Model model, HttpSession session) {
        return "basket";
    }

    @GetMapping("/payment")
    public String payment(Model model, HttpSession session) {
        return "payment";
    }

    @GetMapping("/selected")
    public String selected(Model model, HttpSession session) {
        return "selected";
    }
}
