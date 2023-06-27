package com.smanga.proyecto.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smanga.proyecto.entity.Manga;
import com.smanga.proyecto.service.MangaService;

@Controller
public class IndexController {
	
	@Autowired
	private MangaService serviManga;

	@RequestMapping("/index")
	public String inicio(Model model) {	
		List<Manga> data = serviManga.listarMangas();
		Collections.reverse(data);
		model.addAttribute("dataManga", data);	
		return "index";
	}
}
