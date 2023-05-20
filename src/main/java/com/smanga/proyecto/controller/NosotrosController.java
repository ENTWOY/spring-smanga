package com.smanga.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NosotrosController {
	
	@RequestMapping("/nosotros")
	public String nosotros(Model model) {
		return "nosotros";
	}
}
