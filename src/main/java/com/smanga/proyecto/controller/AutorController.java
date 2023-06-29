package com.smanga.proyecto.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.Autor;
import com.smanga.proyecto.service.AutorService;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/autor")
public class AutorController {

	// injection
	@Autowired
	private AutorService serviAutor;
	
	// list
	@RequestMapping("/lista")
	public String inicio(Model model) {
		
		// recupera listado de los autores
		List<Autor> dataAutor = serviAutor.listarAutores();
				
		// crear el atributo listaAutor para enviar datos a la tabla
		model.addAttribute("listaAutor" ,dataAutor);
		
		// retorna autor.html
		return "autor";
	}
	
	// ruta save and update
	// PUBLIC RedirectView ANTES and RedirectAttributes == model
	@RequestMapping("/grabar")
	// public RedirectView grabar(@RequestParam("txtCodigo") int cod,
	// @RequestParam("txtNombre") String nom, @RequestParam("txtApellido") String ape,
	public String grabar(@RequestParam("txtCodigo") int cod, @RequestParam("txtNombre") String nom, @RequestParam("txtPais") String pai , RedirectAttributes redirect) {

		try {
			// object
			Autor a = new Autor();

			// set
			a.setCodAut(cod);
			a.setNomAut(nom);
			a.setPaiAut(pai);

			// llamar metodo
			serviAutor.grabar(a);

			// message
			if (cod > 0) {
				// si encuentra un cod mayor a 0; actualiza
				// antes: addFlashAttribute
				redirect.addFlashAttribute("mensaje", "Autor " + a.getNomAut().toUpperCase() + " se actualizó correctamente.");
			} else {
				// cod null; registra
				// crear un atributo "mensaje" para enviar al js
				redirect.addFlashAttribute("mensaje", "Autor: "  + a.getNomAut().toUpperCase() + " se registró corectamente.");
			}

		} catch (Exception e) {
			redirect.addAttribute("mensaje", "Ocurrió un error al intentar grabar!");
			e.printStackTrace();
		}

		// return "forward:/autor/lista";
		// return new RedirectView("/autor/lista");
		return "redirect:/autor/lista";
	}

	// ruta search
	@RequestMapping("/buscar")
	@ResponseBody
	public Autor buscar(@RequestParam("buscarCodigo") int cod) {
		// llamar al metodo buscar
		Autor a = serviAutor.buscar(cod);

		return a;
	}

	// ruta eliminar
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {
		
		// object
		Autor objAutor = new Autor();
		objAutor.setCodAut(cod);
		
		try {
			// llamar al metodo eliminar
			serviAutor.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Autor " + "CODIGO: " + objAutor.getCodAut() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace();
		}
		
		return "redirect:/autor/lista";
	}
	
	// REPORTE DE AUTORES
	@RequestMapping("/reporte/{format}")
	public String generarReporte(@PathVariable String format, RedirectAttributes redirect) throws JRException, IOException {
		try {
			serviAutor.exportarAutores(format);
			redirect.addFlashAttribute("mensaje", "Reporte creado exitosamente");
			if(format.equals("pdf"))
				abrirArchivoPdf();
			if(format.equals("html"))
				abrirArchivoHtml();
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Cierra tu pdf actual para poder actualizar el reporte");
		}
		
		return "redirect:/autor/lista";
	}
	// ESTOS METODOS SIRVEN PARA QUE AL MOMENTO DE CREAR TAMBIEN SE ABRA DE MANERA AUTOMATICA
	private void abrirArchivoHtml() {
		try{
			File path = new File("");
			String directoryName = path.getAbsoluteFile().toString();
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler  "+ directoryName+"/reportes"+"/Autores.html");
			}catch(IOException e1){
			System.out.print(e1.toString());
			}	
	}

	private void abrirArchivoPdf() {
		try {
			File path = new File("");
			String directoryName = path.getAbsoluteFile().toString();
		    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + directoryName+"/reportes"+"/Autores.pdf");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
