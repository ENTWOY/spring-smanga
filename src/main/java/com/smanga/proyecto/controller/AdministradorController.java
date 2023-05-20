package com.smanga.proyecto.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.Administrador;
import com.smanga.proyecto.entity.Rol;
import com.smanga.proyecto.service.AdministradorService;
import com.smanga.proyecto.service.RolService;
import com.smanga.proyecto.service.UploadFileService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private AdministradorService serviAdmin;
	@Autowired
	private RolService serviRol;
	@Autowired
	private UploadFileService serviUpFile;
	
	@GetMapping("lista")
	public String listado(Model model) {	
		try { model.addAttribute("listAdmin", serviAdmin.listarAdministradores());
		} catch (Exception e) { e.printStackTrace(); }
		return "administrador";
	}
	
	@GetMapping(value = "/uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = serviUpFile.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/nuevo")
	public String newAdmin(Model model) {
		Administrador admin = new Administrador();
		admin.setCodAdm(0);
		model.addAttribute("admin", admin);
		
		// Llenar el combito rol mantAdministrador
		List<Rol> dataRol = serviRol.listarRoles();
		model.addAttribute("listaRol", dataRol);
		
		return "mantAdministrador";
	}
	
	@PostMapping("/save")
	public String saveAdmin(@Validated @ModelAttribute("admin") Administrador admin, BindingResult result, Model model,
			@RequestParam("file") MultipartFile image, RedirectAttributes flash, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "mantAdministrador";
		} else {
			if (!image.isEmpty()) {
				if (admin.getCodAdm() > 0 && admin.getImage() != null && admin.getImage().length() > 0) {
					serviUpFile.delete(admin.getImage());
				}
				String uniqueFileName = serviUpFile.copy(image);
				admin.setImage(uniqueFileName);
			}
			try { 
				serviAdmin.save(admin);
				if (admin.getCodAdm() > 0) {
					flash.addFlashAttribute("mensaje", "Administrador " + admin.getNomAdm().toUpperCase() + " se actualizó correctamente.");					
				} else {
					flash.addFlashAttribute("mensaje", "Administrador " + admin.getNomAdm().toUpperCase() + " se registró correctamente.");										
				}
			} catch (Exception e) { 
				flash.addFlashAttribute("mensaje", "Se produjo un error al grabar este registro!");
				e.printStackTrace(); 
			}
			status.setComplete();
		}
		return "redirect:/administrador/lista";
	}
	
	@RequestMapping("/actualizar/{id}")
	public String goUpdate(@PathVariable(value = "id") int id, Model model) {
		Administrador admin = serviAdmin.listById(id);
		model.addAttribute("admin", admin);
		
		// Llenar el combito rol mantAdministrador
		List<Rol> dataRol = serviRol.listarRoles();
		model.addAttribute("listaRol", dataRol);
		
		return "mantAdministrador";
	}
	
	@RequestMapping("/detalle/{id}")
	public String goDetail(@PathVariable(value = "id") int id, Model model) {
		Administrador admin = serviAdmin.listById(id);
		model.addAttribute("admin", admin);
		return "administrador";
	}
	
	@RequestMapping("/delete/{id}")
	public String eliminar(@PathVariable(value = "id") int id, Model model, RedirectAttributes attributes) {
		Administrador objAdmin = new Administrador();
		objAdmin.setCodAdm(id);
		try { 
			serviAdmin.eliminarByID(id); 
			attributes.addFlashAttribute("mensaje", "El administrador con ID " + objAdmin.getCodAdm()  + " ha sido eliminado del sistema.");
		} catch (Exception e) { 
			attributes.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace(); 
		}
		return "redirect:/administrador/lista";
	}

}
