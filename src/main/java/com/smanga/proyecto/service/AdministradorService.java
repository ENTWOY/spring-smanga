package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Administrador;
import com.smanga.proyecto.repository.AdministradorRepository;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository repoAdministrador;
	
	// Agregar y Actualizar
	public void save(Administrador admin) {
		repoAdministrador.save(admin);
	}
	
	// Listado Administrador
	public List<Administrador> listarAdministradores(){
		return repoAdministrador.findAll();
	}
	
	// Eliminar
	public void eliminarByID(int id) {
		repoAdministrador.deleteById(id);
	}
	
	// List X ID
	public Administrador listById(int id) {
		return repoAdministrador.findById(id).get();
	}

	// buscar administrador en alquiler boleta
	public List<Administrador> listarAdministradorXApellido(String ape){
		return repoAdministrador.listApellidoAdministrador(ape);
	}
}
