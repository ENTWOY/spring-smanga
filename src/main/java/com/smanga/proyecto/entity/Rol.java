package com.smanga.proyecto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_rol")
	private Integer codRol;
	
	@Column(name = "nom_rol")
	private String nomRol;
	
	// important!, ignora json para buscar por cod(update)
	@JsonIgnore
	// relation one to many
	@OneToMany(mappedBy = "usuarioRol") // 2.ASOC
	private List<Usuario> listaUsuarios; // list
	
	// important!
	@JsonIgnore
	// relation one to many
	@OneToMany(mappedBy = "AdministradorRol") // 2.ASOC
	private List<Administrador> listaAdministradores; // list

	// Getters And Setters
	public Integer getCodRol() {
		return codRol;
	}

	public void setCodRol(Integer codRol) {
		this.codRol = codRol;
	}

	public String getNomRol() {
		return nomRol;
	}

	public void setNomRol(String nomRol) {
		this.nomRol = nomRol;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Administrador> getListaAdministradores() {
		return listaAdministradores;
	}

	public void setListaAdministradores(List<Administrador> listaAdministradores) {
		this.listaAdministradores = listaAdministradores;
	}

	// Constructors
	public Rol() {
		super();
	}

	public Rol(Integer codRol, String nomRol, List<Usuario> listaUsuarios, List<Administrador> listaAdministradores) {
		super();
		this.codRol = codRol;
		this.nomRol = nomRol;
		this.listaUsuarios = listaUsuarios;
		this.listaAdministradores = listaAdministradores;
	}
}
