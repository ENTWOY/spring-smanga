package com.smanga.proyecto.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ModelAttribute
    public void addAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username.toUpperCase());
        System.out.println("Mi rol es: " + username);
    }
}
