package com.smanga.proyecto.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.smanga.proyecto.entity.Autor;
import com.smanga.proyecto.repository.AutorRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class AutorService {
	
	// inyeccion
	@Autowired
	private AutorRepository autorRepo;
	
	// metodos
	// listado
	public List<Autor> listarAutores(){
		return autorRepo.findAll();
	}
	
	// registra y actualiza
	public void grabar(Autor bean) {
		autorRepo.save(bean);
	}
	
	// buscar (si existe; devuelve un autor con valor, si no devuelve nulo)
	public Autor buscar(Integer cod) {
		return autorRepo.findById(cod).orElse(null);
	}
	
	// eliminar
	public void eliminar(Integer cod) {
		autorRepo.deleteById(cod);
	}
	
	// REPORTE DE AUTORES
	public void exportarAutores(String format) throws JRException, IOException {
		// RECUPERAMOS LA LISTA
		List<Autor> listaAutores = autorRepo.findAll();
		// OBTENIENDO LA RUTA ACTUAL DEL PROYECTO
		File path = new File("");
		String directoryName = path.getAbsoluteFile().toString();
		// LLENANDO LA TABLA DE JASPER
		File file = ResourceUtils.getFile("classpath:jasper/ReporteAutor.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaAutores);
		// LLENANDO ALGUN PARAMETRO QUE SE HAYA DEFINIDO EN JASPER
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("createdBy", "Team S-Manga");
	    parameters.put("logo", directoryName+"\\src\\main\\resources\\static\\resources\\img\\logo.png");
	    parameters.put("github", directoryName+"\\src\\main\\resources\\static\\resources\\img\\github.jpg");
		parameters.put("support", directoryName+"\\src\\main\\resources\\static\\resources\\img\\support.jpg");
	    // VISUALIZANDO LA TABLA DE JASPER
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, directoryName+"\\reportes"+"\\Autores.pdf");
		}
		if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, directoryName+"\\reportes"+"\\Autores.html");
		}
	}
}






















