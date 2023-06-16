package com.smanga.proyecto.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.smanga.proyecto.entity.Usuario;
import com.smanga.proyecto.repository.UsuarioRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class UsuarioService{
	// INJECTION
	@Autowired
	private UsuarioRepository usuarioRepo;
	// lIST
	public List<Usuario> listarUsuarios() {
		return usuarioRepo.findAll();
	}
	// SAVE AND UPDATE
	public void grabar(Usuario bean) {
		usuarioRepo.save(bean);
	}
	// BUSCAR POR ID O DEVOLVER NULL
	public Usuario buscar(Integer cod) {
		return usuarioRepo.findById(cod).orElse(null);
	}
	// DELETE
	public void eliminar(Integer cod) {
		usuarioRepo.deleteById(cod);
	}
	// FIND IN TABLE ALQUILER FOR APELLIDO DE USUARIO
	public List<Usuario> listarUsuarioXApellido(String ape){
		return usuarioRepo.listApellidoUsuario(ape);
	}
	/*
	 * SPRING SECURITY
	 * */
	/*
	public Usuario signup(Usuario person) {
		person.setClave(getPasswordEncoder().encode(person.getClave()));
		return usuarioRepo.save(person);
	}
	
	public boolean login(Usuario person) {
		Usuario dbPerson = usuarioRepo.findByUserUsuAndClave(person.getUserUsu(), person.getClave());
		boolean loginStatus = dbPerson != null ? true : false;
		return loginStatus;
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 * */
	public void exportarUsuarios(String format) throws JRException, IOException {
		// RECUPERAMOS LA LISTA
		List<Usuario> listaUsuarios = usuarioRepo.findAll();
		// OBTENIENDO RUTA ACTUAL DEL PROYECTO
		File path = new File("");
		String directoryName = path.getAbsolutePath().toString();
		// LLENANDO LA TABLA DE JASPER
		File file = ResourceUtils.getFile("classpath:jasper/ReporteUsuario.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaUsuarios);
		// LLENANDO ALGUN PARAMETRO QUE SE HAYA DEFINIDO EN JASPER
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Team S-Manga");
		parameters.put("logo", directoryName+"\\src\\main\\resources\\static\\resources\\img\\logo.png");
		parameters.put("github", directoryName+"\\src\\main\\resources\\static\\resources\\img\\github.jpg");
		parameters.put("support", directoryName+"\\src\\main\\resources\\static\\resources\\img\\support.jpg");
		// VISUALIZANDO LA TABLA DE JASPER
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, directoryName+"\\reportes"+"\\Usuarios.pdf");
		}
		if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, directoryName+"\\reportes"+"\\Usuarios.html");
		}
	}
}
