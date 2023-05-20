package com.smanga.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smanga.proyecto.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	/*
	 * Método para buscar a una persona en la base de datos 
	 * que tenga el nombre de usuario y contraseña especificado
	 * */
	// public Usuario findByUserUsuAndClave(String usuario, String clave);

	// public Usuario findByUserUsu(String usuario);
	
	@Query("select u from Usuario u where u.apeUsu like ?1")
	public List<Usuario> listApellidoUsuario(String ape);
}
