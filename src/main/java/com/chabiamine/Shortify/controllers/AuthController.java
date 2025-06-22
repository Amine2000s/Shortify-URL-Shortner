package com.chabiamine.Shortify.controllers;

import com.chabiamine.Shortify.models.Users;
import com.chabiamine.Shortify.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UsersRepository usersRepository
            , PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new Users());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute Users user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}
