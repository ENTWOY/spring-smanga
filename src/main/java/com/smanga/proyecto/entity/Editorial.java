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
@Table(name = "tb_editorial")
public class Editorial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_edi")
	private Integer codEdi;
	
	@Column(name = "nom_edi")
	private String nomEdi;
	
	// pais
	@Column(name = "pai_edi")
	private String paiEdi;
	
	@Column(name = "dir_edi")
	private String dirEdi;
	
	// email
	@Column(name = "ema_edi")
	private String emaEdi;
	
	// telefono
	@Column(name = "tel_edi")
	private int telEdi;
	
	// important!, ignora json para buscar por cod(update)
	@JsonIgnore
	// relacion uno a muchos
	@OneToMany(mappedBy = "mangaEditorial") // 2.ASOC
	private List<Manga> listaMangas; // list

	// getters and setters
	public Integer getCodEdi() {
		return codEdi;
	}

	public void setCodEdi(Integer codEdi) {
		this.codEdi = codEdi;
	}

	public String getNomEdi() {
		return nomEdi;
	}

	public void setNomEdi(String nomEdi) {
		this.nomEdi = nomEdi;
	}

	public String getPaiEdi() {
		return paiEdi;
	}

	public void setPaiEdi(String paiEdi) {
		this.paiEdi = paiEdi;
	}

	public String getDirEdi() {
		return dirEdi;
	}

	public void setDirEdi(String dirEdi) {
		this.dirEdi = dirEdi;
	}

	public String getEmaEdi() {
		return emaEdi;
	}

	public void setEmaEdi(String emaEdi) {
		this.emaEdi = emaEdi;
	}

	public int getTelEdi() {
		return telEdi;
	}

	public void setTelEdi(int telEdi) {
		this.telEdi = telEdi;
	}

	public List<Manga> getListaMangas() {
		return listaMangas;
	}

	public void setListaMangas(List<Manga> listaMangas) {
		this.listaMangas = listaMangas;
	}
	
	// constructor vacio
	public Editorial() {
			
	}
}
