package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Usuario;
import com.smanga.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
	// injection
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	// listado
	public List<Usuario> listarUsuarios() {
		return usuarioRepo.findAll();
	}
	
	// save and update
	public void grabar(Usuario bean) {
		usuarioRepo.save(bean);
	}
	
	// buscar (si existe devuelve un autor con valor, si no devuelve nulo)
	public Usuario buscar(Integer cod) {
		return usuarioRepo.findById(cod).orElse(null);
	}

	// eliminar
	public void eliminar(Integer cod) {
		usuarioRepo.deleteById(cod);
	}
	
	// buscar usuario en alquiler boleta
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
}
