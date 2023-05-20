package com.smanga.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactoController {
	
	@RequestMapping("/contacto")
	public String contacto(Model model) {
		return "contacto";
	}
}
