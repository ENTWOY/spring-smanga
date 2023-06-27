package com.smanga.proyecto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_usu")
	private Integer codUsu;
	
	@Column(name = "nom_usu")
	private String nomUsu;

	@Column(name = "ape_usu")
	private String apeUsu;
	
	@Column(name = "dni_usu")
	private int dniUsu;
	
	@Column(name = "ema_usu")
	private String emaUsu;
	
	@Column(name = "tel_usu")
	private int telUsu;
	
	@Column(name = "dir_usu")
	private String dirUsu;
	
	// user
	@Column(name = "user_usu")
	private String userUsu;
	
	// password
	@Column(name = "clave")
	private String clave;
	
	// relation many to one
	@ManyToOne
	@JoinColumn(name = "cod_rol") // key foranea
	private Rol usuarioRol; // 1.ASOC.
	
	@JsonIgnore
	@OneToMany(mappedBy = "alquilerUsuario")
	private List<Alquiler> listaAlquiler;

	// getters and setters
	public Integer getCodUsu() {
		return codUsu;
	}

	public void setCodUsu(Integer codUsu) {
		this.codUsu = codUsu;
	}

	public String getNomUsu() {
		return nomUsu;
	}

	public void setNomUsu(String nomUsu) {
		this.nomUsu = nomUsu;
	}

	public String getApeUsu() {
		return apeUsu;
	}

	public void setApeUsu(String apeUsu) {
		this.apeUsu = apeUsu;
	}

	public int getDniUsu() {
		return dniUsu;
	}

	public void setDniUsu(int dniUsu) {
		this.dniUsu = dniUsu;
	}

	public String getEmaUsu() {
		return emaUsu;
	}

	public void setEmaUsu(String emaUsu) {
		this.emaUsu = emaUsu;
	}

	public int getTelUsu() {
		return telUsu;
	}

	public void setTelUsu(int telUsu) {
		this.telUsu = telUsu;
	}

	public String getDirUsu() {
		return dirUsu;
	}

	public void setDirUsu(String dirUsu) {
		this.dirUsu = dirUsu;
	}

	public String getUserUsu() {
		return userUsu;
	}

	public void setUserUsu(String userUsu) {
		this.userUsu = userUsu;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Rol getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(Rol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	
	public List<Alquiler> getListaAlquiler() {
		return listaAlquiler;
	}

	public void setListaAlquiler(List<Alquiler> listaAlquiler) {
		this.listaAlquiler = listaAlquiler;
	}

	// constructors
	public Usuario(Integer codUsu, String nomUsu, String apeUsu, int dniUsu, String emaUsu, int telUsu,
			String dirUsu, String userUsu, String clave, Rol usuarioRol) {
		super();
		this.codUsu = codUsu;
		this.nomUsu = nomUsu;
		this.apeUsu = apeUsu;
		this.dniUsu = dniUsu;
		this.emaUsu = emaUsu;
		this.telUsu = telUsu;
		this.dirUsu = dirUsu;
		this.userUsu = userUsu;
		this.clave = clave;
		this.usuarioRol = usuarioRol;
	}

	public Usuario(Integer codUsu, String nomUsu, String apeUsu, int dniUsu, String emaUsu, int telUsu,
			String dirUsu) {
		super();
		this.codUsu = codUsu;
		this.nomUsu = nomUsu;
		this.apeUsu = apeUsu;
		this.dniUsu = dniUsu;
		this.emaUsu = emaUsu;
		this.telUsu = telUsu;
		this.dirUsu = dirUsu;
	}

	public Usuario() {
		super();
	}
}
