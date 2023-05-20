package com.smanga.proyecto.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	// Esta variable se utiliza para especificar la ubicación de la carpeta
	// donde se guardarán los archivos cargados
	private final static String UPLOADS_FOLDER = "uploads";
	
	// Metodo para cargar un archivo desde una ubicación especificada en la aplicación
	public Resource load(String filename) throws MalformedURLException {
		Path path = getPath(filename);
		Resource resource = new UrlResource(path.toUri());
		
		if (!resource.exists() || !resource.isReadable()) {
			throw new RuntimeException("Error in path: " + path.toString());
		}
		return resource;
	}
	
	// Método para copiar un archivo en una ubicación específica en la aplicación
	public String copy(MultipartFile file) throws IOException {
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		Files.copy(file.getInputStream(), rootPath);
		return uniqueFilename;
	}
	
	// Metodo para eliminar un archivo de una ubicación específica en la aplicación
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File file = rootPath.toFile();
		if(file.exists() && file.canRead()) {
			if(file.delete()) {
				return true;
			}
		}
		return false;
	}
	
	// Metodo para obtener la ubicación de un archivo en el sistema de archivos de la aplicación.
	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}
}
