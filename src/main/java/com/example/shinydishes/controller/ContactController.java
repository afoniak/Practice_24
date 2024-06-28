package com.example.shinydishes.controller;

import com.example.shinydishes.models.Post;
import com.example.shinydishes.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @PostMapping("/contact")
    public String contactAdd (@RequestParam String name, @RequestParam String email, @RequestParam String message, Model model) {
        Post post = new Post(name,email,message);
        postRepository.save(post);
        return "contact";
    }
}
