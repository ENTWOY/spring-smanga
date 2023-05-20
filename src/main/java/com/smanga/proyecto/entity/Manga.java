package com.smanga.proyecto.entity;

import java.math.BigDecimal;
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
@Table(name = "tb_libro")
public class Manga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_lib")
	private Integer codLib;
	
	@Column(name = "tit_lib")
	private String titLib;
	
	@Column(name = "des_lib")
	private String desLib;
	
	@Column(name = "anio_lib")
	private int AnioLib;
	
	@Column(name = "gen_lib")
	private String genLib;
	
	@Column(name = "stock_lib")
	private int stockLib;
	
	// volumen manga
	@Column(name = "vol_lib")
	private int volLib;
	
	@Column(name = "precio_alquiler_dia")
	private BigDecimal precioAlquilerDia;
	
	// no column name
	private byte[] img_lib;
	
	@Column(name = "nom_archivo")
	private String nomArchivo;
	
	// relacion muchos a uno
	@ManyToOne
	@JoinColumn(name = "cod_aut") // key foranea
	private Autor mangaAutor; // 1.ASOC.

	// relacion muchos a uno
	@ManyToOne
	@JoinColumn(name = "cod_edi") // key foranea
	private Editorial mangaEditorial; // 1.ASOC.
	
	@JsonIgnore
	@OneToMany(mappedBy = "ADetalleManga")
	private List<AlquilerDetalle> listaADetalle;

	public List<AlquilerDetalle> getListaADetalle() {
		return listaADetalle;
	}

	public void setListaADetalle(List<AlquilerDetalle> listaADetalle) {
		this.listaADetalle = listaADetalle;
	}

	// getters and setters
	public Integer getCodLib() {
		return codLib;
	}

	public void setCodLib(Integer codLib) {
		this.codLib = codLib;
	}

	public String getTitLib() {
		return titLib;
	}

	public void setTitLib(String titLib) {
		this.titLib = titLib;
	}

	public String getDesLib() {
		return desLib;
	}

	public void setDesLib(String desLib) {
		this.desLib = desLib;
	}

	public int getAnioLib() {
		return AnioLib;
	}

	public void setAnioLib(int anioLib) {
		AnioLib = anioLib;
	}

	public String getGenLib() {
		return genLib;
	}

	public void setGenLib(String genLib) {
		this.genLib = genLib;
	}

	public int getStockLib() {
		return stockLib;
	}

	public void setStockLib(int stockLib) {
		this.stockLib = stockLib;
	}

	public int getVolLib() {
		return volLib;
	}

	public void setVolLib(int volLib) {
		this.volLib = volLib;
	}

	public BigDecimal getPrecioAlquilerDia() {
		return precioAlquilerDia;
	}

	public void setPrecioAlquilerDia(BigDecimal precioAlquilerDia) {
		this.precioAlquilerDia = precioAlquilerDia;
	}

	public byte[] getImg_lib() {
		return img_lib;
	}

	public void setImg_lib(byte[] img_lib) {
		this.img_lib = img_lib;
	}

	public String getNomArchivo() {
		return nomArchivo;
	}

	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	public Autor getMangaAutor() {
		return mangaAutor;
	}

	public void setMangaAutor(Autor mangaAutor) {
		this.mangaAutor = mangaAutor;
	}

	public Editorial getMangaEditorial() {
		return mangaEditorial;
	}

	public void setMangaEditorial(Editorial mangaEditorial) {
		this.mangaEditorial = mangaEditorial;
	}
	
	// constructor vacio
	public Manga() {

	}
}
