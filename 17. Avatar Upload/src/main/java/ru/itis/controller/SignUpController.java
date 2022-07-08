package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.dto.SignUpDto;
import ru.itis.service.SignUpService;


@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping
    public String signUp(SignUpDto signUpDto) {
        Long id = signUpService.signUp(signUpDto);
        return "redirect:/profile/" + id;
    }

}
