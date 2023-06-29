package com.smanga.proyecto.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.Autor;
import com.smanga.proyecto.entity.Editorial;
import com.smanga.proyecto.entity.Manga;
import com.smanga.proyecto.service.AutorService;
import com.smanga.proyecto.service.EditorialService;
import com.smanga.proyecto.service.MangaService;

@Controller
@RequestMapping("/manga")
public class MangaController {

	// injection
	@Autowired
	private MangaService serviManga;
	@Autowired
	private AutorService serviAutor;
	@Autowired
	private EditorialService serviEditorial;
	
	// ruta listado
	@RequestMapping("/lista")
	public String inicio(Model model) {
		
		// recuperar listado...
		List<Manga> data = serviManga.listarMangas();
		
		// recuperar list of types of datos para los combitos
		List<Autor> dataAutor = serviAutor.listarAutores();
		List<Editorial> dataEditorial = serviEditorial.listarEditoriales();
				
		// crear el atributo lista... para enviar datos a la tabla
		model.addAttribute("listaManga" ,data);
		// crear el atributo lista... para enviar datos al combito
		model.addAttribute("listaAutor" ,dataAutor);
		model.addAttribute("listaEditorial" ,dataEditorial);
		
		// retorna "".html
		return "manga";
	}
	
	// ruta registrar y actualizar
	// PUBLIC RedirectView ANTES and RedirectAttributes == model
	@RequestMapping("/grabar")
	// public RedirectView grabar(@RequestParam("txtCodigo") int cod,
	// @RequestParam("txtNombre") String nom, @RequestParam("txtApellido") String ape,
	public String grabar(@RequestParam("txtCodigo") int cod, @RequestParam("txtTitulo") String tit, @RequestParam("txtDescripcion") String des , 
			@RequestParam("txtAnio") int anio, @RequestParam("txtGenero") String gen, @RequestParam("txtStock") int stock, 
			 @RequestParam("txtVolumen") int vol, @RequestParam("txtPrecio") BigDecimal pre, @RequestParam("txtAutor") int autor, 
			  @RequestParam("txtEditorial") int editorial, 	RedirectAttributes redirect) {

		try {
			// object
			Manga objManga = new Manga();

			// set
			objManga.setCodLib(cod);
			objManga.setTitLib(tit);
			objManga.setDesLib(des);
			objManga.setAnioLib(anio);
			objManga.setGenLib(gen);
			objManga.setStockLib(stock);
			objManga.setVolLib(vol);
			objManga.setPrecioAlquilerDia(pre);
			
			// create other object
			Autor objAutor = new Autor();
			objAutor.setCodAut(autor);
			
			// create other object
			Editorial objEditorial = new Editorial();
			objEditorial.setCodEdi(editorial);
			
			// send object
			objManga.setMangaAutor(objAutor);
			objManga.setMangaEditorial(objEditorial);
			
			// llamar metodo y object
			serviManga.grabar(objManga);

			// message
			if (cod > 0) {
				// si encuentra un cod mayor a 0; actualiza
				// antes: addFlashAttribute
				redirect.addFlashAttribute("mensaje", "Manga " + objManga.getTitLib().toUpperCase() + " se actualizó correctamente.");
			} else {
				// si cod null; registra
				// crear un atributo "mensaje" para enviar al js
				redirect.addFlashAttribute("mensaje", "Manga "  + objManga.getTitLib().toUpperCase() + " se registró correctamente.");
			}

		} catch (Exception e) {
			redirect.addAttribute("mensaje", "Ocurrió un error al intentar grabar!");
			e.printStackTrace();
		}

		// return "forward:/autor/lista";
		// return new RedirectView("/autor/lista");
		return "redirect:/manga/lista";
	}

	// ruta buscar
	@RequestMapping("/buscar")
	@ResponseBody
	public Manga buscar(@RequestParam("buscarCodigo") int cod) {
		// llamar al metodo buscar
		Manga m = serviManga.buscar(cod);
		
		return m;
	}

	// ruta eliminar
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {
	
		// object
		Manga objManga = new Manga();
		objManga.setCodLib(cod);
		
		try {
			// llamar al metodo eliminar
			serviManga.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Manga " + "CODIGO: " + objManga.getCodLib() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace();
		}
		
		// retornar "".html
		return "redirect:/manga/lista";
	}
	
	@RequestMapping("/detalle/{id}")
	public String goDetail(@PathVariable(value = "id") int id, Model model) {
		Manga manga = serviManga.listById(id);
		model.addAttribute("mangaDetalle", manga);
		return "detalle";
	}
	
	// ruta img y archivo
	@RequestMapping("/imagen-manga")
	public String subirArchivo(@RequestParam("dataArchivo") MultipartFile archivo, @RequestParam("txtCodigo") Integer cod,
			RedirectAttributes redirect) throws IOException {
		
		// almacena el archivo
		String nomArchivoString = archivo.getOriginalFilename();
		
		// guardar en un arreglo byte
		byte[] bytes = archivo.getBytes();
		
		// ruta del proyecto(interno) necesario: refresh
		// String ruta=".//src//main//resources//static//files//";
		
		// ruta de escritorio(externo) automatico
		// String ruta="C://Users//Usuario//Desktop//soImportant//datosImg//";
		
		// ruta de USB(externo)
		String ruta = "D://saveIMG//";
		
		// generar archivo
		Files.write(Paths.get(ruta + nomArchivoString), bytes);
		
		// completar parametros
		serviManga.actualizarImg(bytes, nomArchivoString, cod);
		
		// message
		redirect.addFlashAttribute("mensaje", "Imagen " + nomArchivoString.toUpperCase() + " se actualizó correctamente.");
		
		// retornar "".html
		return "redirect:/manga/lista";
	}
	
}
