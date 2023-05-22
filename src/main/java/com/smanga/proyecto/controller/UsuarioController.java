package com.smanga.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.Rol;
import com.smanga.proyecto.entity.Usuario;
import com.smanga.proyecto.service.RolService;
import com.smanga.proyecto.service.UsuarioService;

@Controller
// @RequestMapping("/usuario")
public class UsuarioController {

	// injection
	@Autowired
	private UsuarioService serviUsuario;
	@Autowired
	private RolService serviRol;
	
	// login
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	// LISTADO
	@RequestMapping("/usuario/lista")
	public String inicio(Model model) {
		// RECUPERAR LISTADO
		List<Usuario> data = serviUsuario.listarUsuarios();
		List<Rol> dataRol = serviRol.listarRoles();
		// CREAR ATRIBUTO LISTA PARA ENVIARLO AL FRONTED
		model.addAttribute("listaUsuario", data);
		model.addAttribute("listaRol", dataRol);
		// RETORNAR EN "...".HTML
		return "usuario";
	}
	// RUTA DE SAVE O UPDATE
	@RequestMapping("/usuario/grabar")
	public String grabar(@RequestParam("txtCodigo") int cod, @RequestParam("txtNombre") String nom, @RequestParam("txtApellido") String ape, 
			@RequestParam("txtDni") int dni, @RequestParam("txtEmail") String ema, @RequestParam("txtTelefono") int tel, 
				@RequestParam("txtDireccion") String dir, @RequestParam("txtUsuario") String usu, @RequestParam("txtClave") String clave, 
					@RequestParam("txtRol") int rol,  RedirectAttributes redirect) {
		try {
			// NEW OBJECT
			Usuario u = new Usuario();
			// SETS
			u.setCodUsu(cod);
			u.setNomUsu(nom);
			u.setApeUsu(ape);
			u.setDniUsu(dni);
			u.setEmaUsu(ema);
			u.setTelUsu(tel);
			u.setDirUsu(dir);
			u.setUserUsu(usu);
			u.setClave(clave);
			// OBJECT
			Rol objRol = new Rol();
			objRol.setCodRol(rol);		
			// SETS
			u.setUsuarioRol(objRol);
			// METODO PARA GRABAR
			serviUsuario.grabar(u);
			// MESSAGE
			if (cod > 0) {
				// SI ENCUENTRA UN CODIGO DIFERENTE A 0, ACTUALIZA
				redirect.addFlashAttribute("mensaje", "Usuario " + u.getNomUsu().toUpperCase() + " se actualizó correctamente.");
			} else {
				// COD NULL REGISTRA
				// CREAR UN ATRIBUTO MENSAJE PARA ENVIAR AL JS
				redirect.addFlashAttribute("mensaje", "Usuario: " + u.getNomUsu().toUpperCase() + " se registró corectamente.");
			}
		} catch (Exception e) {
			redirect.addAttribute("mensaje", "Ocurrió un error al intentar grabar!");
			e.printStackTrace();
		}
		return "redirect:/usuario/lista";
	}
	
	// RUTA BUSCAR
	@RequestMapping("/usuario/buscar")
	@ResponseBody
	public Usuario buscar(@RequestParam("buscarCodigo") int cod) {
		// LLAMAR AL METODO BUSCAR
		Usuario u = serviUsuario.buscar(cod);
		return u;
	}
	// RUTA ELIMINAR
	@RequestMapping("/usuario/eliminar")
	public String eliminar(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {
		// OBJECT
		Usuario objUser = new Usuario();
		objUser.setCodUsu(cod);
		try {
			// LLAMAR AL METODO ELIMINAR
			serviUsuario.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Usuario " + "CODIGO: " + objUser.getCodUsu() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace();
		}
		// RETONAR "".HTML
		return "redirect:/usuario/lista";
	}
}
