package com.smanga.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.AlquilerDetalle;
import com.smanga.proyecto.service.AlquilerDetalleService;

@Controller
@RequestMapping("/detalles")
public class AlquilerDetalleController {
	
	@Autowired
	private AlquilerDetalleService serviAlquilerDetalle;

	@RequestMapping("/lista")
	public String detalles(Model model) {
		List<AlquilerDetalle> detalles = serviAlquilerDetalle.listaDetalles();
		model.addAttribute("dataDetalles", detalles);
		return "mantDetalle";
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {
		AlquilerDetalle objAlquilerDetalle = new AlquilerDetalle();
		objAlquilerDetalle.setCodDetAlq(cod);
		try {
			serviAlquilerDetalle.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Alquiler Detalle " + "ID: " + objAlquilerDetalle.getCodDetAlq() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro!");
			e.printStackTrace();
		}
		return "redirect:/detalles/lista";
	}
}
