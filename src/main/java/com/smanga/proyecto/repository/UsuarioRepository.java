package com.smanga.proyecto.repository;

import java.util.List;
import java.util.Optional;

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
	
	//Método para poder buscar un usuario mediante su nombre
    // Optional<Usuario> findByUserUsu(String username);

    //Método para poder verificar si un usuario existe en nuestra base de datos
    // Boolean existsByUserUsu(String username);
	
	@Query("select u from Usuario u where u.apeUsu like ?1")
	public List<Usuario> listApellidoUsuario(String ape);
	
	public Usuario findByUserUsu(String username);
}
