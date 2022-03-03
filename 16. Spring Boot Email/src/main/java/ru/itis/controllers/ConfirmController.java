package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.SignUpService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/confirm/{code}")
public class ConfirmController {
    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(@PathVariable String code, Model model) {
        boolean isActivated = signUpService.confirm(code);
        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "confirm";
    }
}
