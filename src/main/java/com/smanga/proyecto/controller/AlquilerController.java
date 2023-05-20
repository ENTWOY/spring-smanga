package com.smanga.proyecto.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smanga.proyecto.entity.Administrador;
import com.smanga.proyecto.entity.Alquiler;
import com.smanga.proyecto.entity.AlquilerDetalle;
import com.smanga.proyecto.entity.Autor;
import com.smanga.proyecto.entity.Detalle;
import com.smanga.proyecto.entity.Manga;
import com.smanga.proyecto.entity.Usuario;
import com.smanga.proyecto.service.AdministradorService;
import com.smanga.proyecto.service.AlquilerService;
import com.smanga.proyecto.service.MangaService;
import com.smanga.proyecto.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/alquiler")
public class AlquilerController {
	
	@Autowired
	private MangaService serviManga;
	@Autowired
	private UsuarioService serviUsuario;
	@Autowired
	private AdministradorService serviAdministrador;
	@Autowired
	private AlquilerService serviAlquiler;
	
	/* 
	 * ***************************
	 * MantenimientoAlquiler(CRUD) 
	 * ***************************
	 * */
	@RequestMapping("/lista")
	public String listado(Model model) {
		List<Alquiler> dataAlquiler = serviAlquiler.listarAlquileres();
		model.addAttribute("dataAlquiler", dataAlquiler);
		return "mantAlquiler";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("txtCodigo") int cod, @RequestParam("txtFecAlq") String fecAlq, 
			@RequestParam("txtFecDev") String fecDev, @RequestParam("txtMonto") BigDecimal monto,
			@RequestParam("txtEstado") String estado, @RequestParam("txtUsuario") int usuario, 
			@RequestParam("txtAdministrador") int administrador, RedirectAttributes redirect) {
		try {
			Alquiler objAlquiler = new Alquiler();

			objAlquiler.setCodAlq(cod);
			objAlquiler.setFecAlq(new SimpleDateFormat("yyyy-MM-dd").parse(fecAlq));
			objAlquiler.setFecDev(new SimpleDateFormat("yyyy-MM-dd").parse(fecDev));
			objAlquiler.setMontoAlq(monto);
			objAlquiler.setEstado(estado);
			
			Usuario objUsuario = new Usuario();
			objUsuario.setCodUsu(usuario);
			
			Administrador objAdmin = new Administrador();
			objAdmin.setCodAdm(administrador);
			
			objAlquiler.setAlquilerUsuario(objUsuario);
			objAlquiler.setAlquilerAdministrador(objAdmin);

			serviAlquiler.actualizar(objAlquiler);

			if (cod > 0) { redirect.addFlashAttribute("mensaje", "El estado se actualizó correctamente a: " + objAlquiler.getEstado().toUpperCase());
			} else { redirect.addFlashAttribute("mensaje", "null"); }
		} catch (Exception e) {
			redirect.addAttribute("mensaje", "Ocurrió un error al intentar actualizar!");
			e.printStackTrace();
		}
		return "redirect:/alquiler/lista";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Alquiler buscar(@RequestParam("buscarCodigo") int cod) {
		Alquiler objAlquiler = serviAlquiler.buscar(cod);
		return objAlquiler;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("buscarCodigo") int cod, RedirectAttributes redirect) {
		Alquiler objAlquiler = new Alquiler();
		objAlquiler.setCodAlq(cod);
		try {
			serviAlquiler.eliminar(cod);
			redirect.addFlashAttribute("mensaje", "Alquiler " + "ID: " + objAlquiler.getCodAlq() + " eliminado.");
		} catch (Exception e) {
			redirect.addFlashAttribute("mensaje", "Se produjo un error al eliminar este registro. Es posible que tenga una relación con otra tabla en la base de datos, lo que impide su eliminación!");
			e.printStackTrace();
		}
		return "redirect:/alquiler/lista";
	}
	/* 
	 * ***************************
	 * */
	
	/* 
	 * *******************
	 * TrasaccionAlquiler 
	 * *******************
	 * */
	@RequestMapping("/boleta")
	public String alquiler(Model model) {
		// Listado de Mangas
		model.addAttribute("listMangas", serviManga.listarMangas()); 	
		return "alquiler";
	}
	
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adicionar(@RequestParam("codigo") int cod, 
			@RequestParam("nombre") String nom, @RequestParam("cantidad") int can, 
			@RequestParam("precio") BigDecimal precio, HttpSession session) {	
		// Clase Detalle
		List<Detalle> data = null;
		
		if(session.getAttribute("carrito") == null) {
			// create arreglo data
			data = new ArrayList<Detalle>();
		} else { // recuperar y guardarlo
			data = (List<Detalle>) session.getAttribute("carrito");
		}
		
		// Verificar si el objeto ya existe en el arreglo
		for (Detalle d : data) {
			if (d.getCodigo() == cod) {
				// Si ya existe, se actualiza la cantidad
				d.setCantidad(d.getCantidad() + can);
				return data;
			}
		}
		
		// create object of class Details
		Detalle objDetalle = new Detalle();
		// sets
		objDetalle.setCodigo(cod);
		objDetalle.setNombre(nom);
		objDetalle.setCantidad(can);
		objDetalle.setPrecio(precio);
		
		// adicionar objDetalle dentro del arreglo "data"
		data.add(objDetalle);
		
		// create atributo de type session "carrito"
		session.setAttribute("carrito", data);
		
		int cantidadRegistros = data.size();
		session.setAttribute("cantidadRegistros", cantidadRegistros);
		
		return data;
	}
	
	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Detalle> eliminar(@RequestParam("codigo") int cod, HttpSession session){
		// recuperar y guardarlo
		List<Detalle> data = (List<Detalle>) session.getAttribute("carrito");
		
		// Iterar
		for(Detalle d : data) {
			if(d.getCodigo() == cod) {
				data.remove(d);
				break;
			}
		}
		
		// create atributo de type session "carrito"
		session.setAttribute("carrito", data);	
		return data;
	}
	
	@RequestMapping("/listarUsuarios")
	@ResponseBody
	public List<Usuario> listarUsuarios(@RequestParam("apellido") String ape){
		List<Usuario> data = serviUsuario.listarUsuarioXApellido(ape+"%");
		return data;
	}
	
	@RequestMapping("/listarAdministradores")
	@ResponseBody
	public List<Administrador> listarAdministradores(@RequestParam("apellido") String ape){
		List<Administrador> dataA = serviAdministrador.listarAdministradorXApellido(ape+"%");
		return dataA;
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("fechaPrestamo") String fecPres, @RequestParam("fechaDevolucion") String fecDev, 
				@RequestParam("codigoCliente") int codCli, @RequestParam("codigoAdmin") int codAdmin,
				@RequestParam("estado") String estado, @RequestParam("monto") BigDecimal monto, HttpSession session, RedirectAttributes redirect) {
		try {
			Alquiler objAlquiler = new Alquiler();
			objAlquiler.setFecAlq(new SimpleDateFormat("yyyy-MM-dd").parse(fecPres));
			objAlquiler.setFecDev(new SimpleDateFormat("yyyy-MM-dd").parse(fecDev));		
			objAlquiler.setMontoAlq(monto);		
			objAlquiler.setEstado(estado);
			
			Usuario usu = new Usuario();
			usu.setCodUsu(codCli);	
			Administrador admi = new Administrador();
			admi.setCodAdm(codAdmin);
			
			objAlquiler.setAlquilerUsuario(usu);
			objAlquiler.setAlquilerAdministrador(admi);
			
			List<Detalle> data=(List<Detalle>) session.getAttribute("carrito");		
			List<AlquilerDetalle> lista = new ArrayList<AlquilerDetalle>();
			
			for(Detalle detail:data) {
				AlquilerDetalle adetalle = new AlquilerDetalle();
				Manga manga = new Manga();
				manga.setCodLib(detail.getCodigo());			
		
				adetalle.setADetalleManga(manga);
				adetalle.setADetalleAlquiler(objAlquiler);	
				adetalle.setPrecio(detail.getPrecio());
				adetalle.setCantidad(detail.getCantidad());	
				lista.add(adetalle);
			}
			objAlquiler.setListaADetalle(lista);
			
			serviAlquiler.registrarAlquiler(objAlquiler);
			data.clear();
			session.setAttribute("carrito", data);
			redirect.addFlashAttribute("mensaje","¡Enhorabuena! El alquiler ha sido registrado con éxito.");
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("mensaje","Ha habido un problema al intentar registrar tu solicitud de alquiler!");
		}
		return "redirect:/alquiler/boleta";
	}
}
