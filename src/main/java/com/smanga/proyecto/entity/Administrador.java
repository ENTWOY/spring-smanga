package com.smanga.proyecto.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_administrador")
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_adm")
	private Integer codAdm;
	
	@Column(name = "nom_adm")
	private String nomAdm;

	@Column(name = "ape_adm")
	private String apeAdm;
	
	@Column(name = "dni_adm")
	private int dniAdm;
	
	@Column(name = "ema_adm")
	private String emaAdm;
	
	@Column(name = "tel_adm")
	private int telAdm;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fec_nac")
	private Date fecNac;
	
	@Column(name = "image")
	private String image;
	
	// user
	@Column(name = "user_adm")
	private String userAdm;
	
	// password
	@Column(name = "clave")
	private String clave;
	
	// relation many to one
	@ManyToOne
	@JoinColumn(name = "cod_rol") // key foranea
	private Rol AdministradorRol; // 1.ASOC.

	@JsonIgnore
	@OneToMany(mappedBy = "alquilerAdministrador")
	private List<Alquiler> listaAlquiler;
	
	public List<Alquiler> getListaAlquiler() {
		return listaAlquiler;
	}

	public void setListaAlquiler(List<Alquiler> listaAlquiler) {
		this.listaAlquiler = listaAlquiler;
	}

	// Getters and Setters
	public Integer getCodAdm() {
		return codAdm;
	}

	public void setCodAdm(Integer codAdm) {
		this.codAdm = codAdm;
	}

	public String getNomAdm() {
		return nomAdm;
	}

	public void setNomAdm(String nomAdm) {
		this.nomAdm = nomAdm;
	}

	public String getApeAdm() {
		return apeAdm;
	}

	public void setApeAdm(String apeAdm) {
		this.apeAdm = apeAdm;
	}

	public int getDniAdm() {
		return dniAdm;
	}

	public void setDniAdm(int dniAdm) {
		this.dniAdm = dniAdm;
	}

	public String getEmaAdm() {
		return emaAdm;
	}

	public void setEmaAdm(String emaAdm) {
		this.emaAdm = emaAdm;
	}

	public int getTelAdm() {
		return telAdm;
	}

	public void setTelAdm(int telAdm) {
		this.telAdm = telAdm;
	}

	public Date getFecNac() {
		return fecNac;
	}

	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserAdm() {
		return userAdm;
	}

	public void setUserAdm(String userAdm) {
		this.userAdm = userAdm;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Rol getAdministradorRol() {
		return AdministradorRol;
	}

	public void setAdministradorRol(Rol administradorRol) {
		AdministradorRol = administradorRol;
	}

	// Constructors
	public Administrador() {
		super();
	}

	public Administrador(Integer codAdm, String nomAdm, String apeAdm, int dniAdm, String emaAdm, int telAdm,
			Date fecNac, String image, String userAdm, String clave, Rol administradorRol) {
		super();
		this.codAdm = codAdm;
		this.nomAdm = nomAdm;
		this.apeAdm = apeAdm;
		this.dniAdm = dniAdm;
		this.emaAdm = emaAdm;
		this.telAdm = telAdm;
		this.fecNac = fecNac;
		this.image = image;
		this.userAdm = userAdm;
		this.clave = clave;
		AdministradorRol = administradorRol;
	}
	
}
