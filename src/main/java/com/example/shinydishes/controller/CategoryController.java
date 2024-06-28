package com.example.shinydishes.controller;

import com.example.shinydishes.models.CategoryDish;
import com.example.shinydishes.repositories.CategoryDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryDishController {

    @Autowired
    private CategoryDishRepository categoryDishRepository;

    @GetMapping("/categoryDish")
    public String categoryDish(Model model) {
        List<CategoryDish> soupDishes = categoryDishRepository.findByType("soup");
        List<CategoryDish> teaDishes = categoryDishRepository.findByType("tea");

        model.addAttribute("soupDishes", soupDishes);
        model.addAttribute("teaDishes", teaDishes);

        return "categoryDish";
    }
}
