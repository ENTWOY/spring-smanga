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
@Table(name = "tb_autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_aut")
	private Integer codAut;
	
	@Column(name = "nom_aut")
	private String nomAut;
	
	// pais
	@Column(name = "pai_aut")
	private String paiAut;
	
	// important!, ignora json para buscar por cod(update)
	@JsonIgnore
	// relacion uno a muchos
	@OneToMany(mappedBy = "mangaAutor") // 2.ASOC
	private List<Manga> listaMangas; // list

	// getters and setters
	public Integer getCodAut() {
		return codAut;
	}

	public void setCodAut(Integer codAut) {
		this.codAut = codAut;
	}

	public String getNomAut() {
		return nomAut;
	}

	public void setNomAut(String nomAut) {
		this.nomAut = nomAut;
	}

	public String getPaiAut() {
		return paiAut;
	}

	public void setPaiAut(String paiAut) {
		this.paiAut = paiAut;
	}

	public List<Manga> getListaMangas() {
		return listaMangas;
	}

	public void setListaMangas(List<Manga> listaMangas) {
		this.listaMangas = listaMangas;
	}
	
	// constructor vacio	
	public Autor(Integer codAut, String nomAut, String paiAut, List<Manga> listaMangas) {
		super();
		this.codAut = codAut;
		this.nomAut = nomAut;
		this.paiAut = paiAut;
		this.listaMangas = listaMangas;
	}

	public Autor() {
		super();
	}
}
