package com.smanga.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.Editorial;
import com.smanga.proyecto.service.EditorialService;

@Controller
@RequestMapping("/editorial")
public class EditorialController {

	// injection
	@Autowired
	private EditorialService serviEditorial;
	
	// ruta listado
	@RequestMapping("/lista")
	public String inicio(Model model) {
		
		// recuperar listado...
		List<Editorial> data = serviEditorial.listarEditoriales();
				
		// crear el atributo lista... para enviar datos a la tabla
		model.addAttribute("listaEditorial" ,data);
		
		// retorna "".html
		return "editorial";
	}
	
	// ruta registrar y actualizar
	// PUBLIC RedirectView ANTES and RedirectAttributes == model
	@RequestMapping("/grabar")
	// public RedirectView grabar(@RequestParam("txtCodigo") int cod,
	// @RequestParam("txtNombre") String nom, @RequestParam("txtApellido") String ape,
	public String grabar(@RequestParam("txtCodigo") int cod, @RequestParam("txtNombre") String nom, @RequestParam("txtPais") String pai , 
			@RequestParam("txtDireccion") String dir, @RequestParam("txtEmail") String ema, @RequestParam("txtTelefono") int tel,  RedirectAttributes redirect) {

		try {
			// object
			Editorial e = new Editorial();

			// set
			e.setCodEdi(cod);
			e.setNomEdi(nom);
			e.setPaiEdi(pai);
			e.setDirEdi(dir);
			e.setEmaEdi(ema);
			e.setTelEdi(tel);

			// llamar metodo y object
			serviEditorial.grabar(e);

			// message
			if (cod > 0) {
				// si encuentra un cod mayor a 0; actualiza
				// antes: addFlashAttribute
				redirect.addFlashAttribute("mensaje", "Editorial "  + e.getNomEdi().toUpperCase() + " se actualizó correctamente.");
			} else {
				// si cod null; registra
				// crear un atributo "mensaje" para enviar al js
				redirect.addFlashAttribute("mensaje", "Editorial "  + e.getNomEdi().toUpperCase() + " se registró correctamente.");
			}

		} catch (Exception e) {
			redirect.addAttribute("mensaje", "Ocurrió un error al intentar grabar!");
			e.printStackTrace();
		}

		// return "forward:/autor/lista";
		// return new RedirectView("/autor/lista");
		return "redirect:/editorial/lista";
	}

	// ruta buscar
	@RequestMapping("/buscar")
	@ResponseBody
	public Editorial buscar(@RequestParam("buscarCodigo") int cod) {
		// llamar al metodo buscar
		Editorial e = serviEditorial.buscar(cod);
		
		return e;
	}

	// ruta eliminar
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {
	
		// object
		Editorial objEdi = new Editorial();
		objEdi.setCodEdi(cod);
		
		try {
			// llamar al metodo eliminar
			serviEditorial.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Editorial " + "CODIGO: " + objEdi.getCodEdi() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace();
		}
		
		return "redirect:/editorial/lista";
	}
	
}
