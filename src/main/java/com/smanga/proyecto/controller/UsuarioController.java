package com.smanga.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// ruta listado
	@RequestMapping("/usuario/lista")
	public String inicio(Model model) {

		// recuperar listado...
		List<Usuario> data = serviUsuario.listarUsuarios();
		List<Rol> dataRol = serviRol.listarRoles();

		// crear el atributo lista... para enviar datos a la tabla
		model.addAttribute("listaUsuario", data);
		model.addAttribute("listaRol", dataRol);

		// retorna "".html
		return "usuario";
	}
	
	// ruta save and update
	@RequestMapping("/usuario/grabar")
	public String grabar(@RequestParam("txtCodigo") int cod, @RequestParam("txtNombre") String nom, @RequestParam("txtApellido") String ape, 
			@RequestParam("txtDni") int dni, @RequestParam("txtEmail") String ema, @RequestParam("txtTelefono") int tel, 
				@RequestParam("txtDireccion") String dir, @RequestParam("txtUsuario") String usu, @RequestParam("txtClave") String clave, 
					@RequestParam("txtRol") int rol,  RedirectAttributes redirect) {
		try {
			// object
			Usuario u = new Usuario();

			// set
			u.setCodUsu(cod);
			u.setNomUsu(nom);
			u.setApeUsu(ape);
			u.setDniUsu(dni);
			u.setEmaUsu(ema);
			u.setTelUsu(tel);
			u.setDirUsu(dir);
			u.setUserUsu(usu);
			u.setClave(clave);
			
			// object
			Rol objRol = new Rol();
			objRol.setCodRol(rol);
			
			// set
			u.setUsuarioRol(objRol);

			// llamar metodo
			serviUsuario.grabar(u);

			// message
			if (cod > 0) {
				// si encuentra un cod mayor a 0; actualiza
				redirect.addFlashAttribute("mensaje",
						"Usuario " + u.getNomUsu().toUpperCase() + " se actualizó correctamente.");
			} else {
				// cod null; registra
				// crear un atributo "mensaje" para enviar al js
				redirect.addFlashAttribute("mensaje",
						"Usuario: " + u.getNomUsu().toUpperCase() + " se registró corectamente.");
			}
		} catch (Exception e) {
			redirect.addAttribute("mensaje", "Ocurrió un error al intentar grabar!");
			e.printStackTrace();
		}
		
		return "redirect:/usuario/lista";
	}
	
	// ruta buscar
	@RequestMapping("/usuario/buscar")
	@ResponseBody
	public Usuario buscar(@RequestParam("buscarCodigo") int cod) {
		// llamar al metodo buscar
		Usuario u = serviUsuario.buscar(cod);

		return u;
	}

	// ruta eliminar
	@RequestMapping("/usuario/eliminar")
	public String eliminar(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {

		// object
		Usuario objUser = new Usuario();
		objUser.setCodUsu(cod);

		try {
			// llamar al metodo eliminar
			serviUsuario.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Usuario " + "CODIGO: " + objUser.getCodUsu() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace();
		}

		// retornar "".html
		return "redirect:/usuario/lista";
	}
}
