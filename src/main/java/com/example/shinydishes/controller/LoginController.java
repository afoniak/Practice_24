package com.example.shinydishes.controller;

import com.example.shinydishes.models.Users;
import com.example.shinydishes.repositories.UsersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Users user = usersRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user.getLogin());
            return "redirect:home";
        } else {
            model.addAttribute("error", "Неправильний логін або пароль");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:home";
    }

    @PostMapping("/telegram-login")
    @ResponseBody
    public Map<String, Object> telegramLogin(@RequestBody Map<String, Object> userData, HttpSession session) {
        String telegramUsername = (String) userData.get("username");
        if (telegramUsername == null) {
            return Map.of("success", false, "error", "Username not provided by Telegram");
        }

        Users user = usersRepository.findByEmail(telegramUsername + "@telegram.com");
        if (user == null) {
            user = new Users(telegramUsername, telegramUsername + "@telegram.com", "telegram");
            usersRepository.save(user);
        }

        session.setAttribute("loggedInUser", user.getLogin());
        return Map.of("success", true);
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationAdd(@RequestParam String login, @RequestParam String email,
                                  @RequestParam String password, @RequestParam("password_confirm") String passwordConfirm, Model model) {
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Паролі не співпадають!");
            return "registration";
        }

        if (usersRepository.existsByLogin(login) || usersRepository.existsByEmail(email)) {
            model.addAttribute("error", "Користувач із таким логіном або email вже існує.");
            return "registration";
        }

        try {
            Users newUser = new Users(login, email, password);
            usersRepository.save(newUser);
            return "redirect:/login";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Помилка під час реєстрації користувача.");
            return "registration";
        }
    }
}
