package com.smanga.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smanga.proyecto.entity.Usuario;
import com.smanga.proyecto.repository.UsuarioRepository;

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
}
