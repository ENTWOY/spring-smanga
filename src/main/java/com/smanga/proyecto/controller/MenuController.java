package com.smanga.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smanga.proyecto.repository.AdministradorRepository;
import com.smanga.proyecto.repository.AlquilerDetalleRepository;
import com.smanga.proyecto.repository.AlquilerRepository;
import com.smanga.proyecto.repository.AutorRepository;
import com.smanga.proyecto.repository.EditorialRepository;
import com.smanga.proyecto.repository.MangaRepository;
import com.smanga.proyecto.repository.UsuarioRepository;

@Controller
public class MenuController {
	
	// injection
	@Autowired
	private UsuarioRepository repoUsuario;
	@Autowired
	private AdministradorRepository repoAdministrador;
	@Autowired
	private MangaRepository repoManga;
	@Autowired
	private EditorialRepository repoEditorial;
	@Autowired
	private AutorRepository repoAutor;
	@Autowired
	private AlquilerRepository repoAlquiler;
	@Autowired
	private AlquilerDetalleRepository repoADetalle;

	@RequestMapping("/menu")
	public String inicio(Model model) {
		
		Long usuarios = repoUsuario.count();
		Long mangas = repoManga.count();
		Long editoriales = repoEditorial.count();
		Long autores = repoAutor.count();
		Long administradores = repoAdministrador.count();
		Long alquileres = repoAlquiler.count();
		Long aDetalles = repoADetalle.count();
		
		model.addAttribute("cantUsuario", usuarios);
		model.addAttribute("cantAdministrador", administradores);
		model.addAttribute("cantManga", mangas);
		model.addAttribute("cantEditorial", editoriales);
		model.addAttribute("cantAutor", autores);
		model.addAttribute("cantAlquiler", alquileres);
		model.addAttribute("cantADetalle", aDetalles);
		
		return "menu";
	}
}
