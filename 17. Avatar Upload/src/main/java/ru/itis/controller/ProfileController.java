package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.service.AccountService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public String getProfile(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getById(id));
        return "profile";
    }
}
