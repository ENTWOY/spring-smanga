package com.smanga.proyecto.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_detalle_alquiler")
public class AlquilerDetalle{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_det_alq")
	private Integer codDetAlq;
	
	@Column(name = "precio")
	private BigDecimal precio;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "cod_lib") 
	private Manga ADetalleManga;
	
	@ManyToOne
	@JoinColumn(name ="cod_alq") 
	private Alquiler ADetalleAlquiler;

	public Integer getCodDetAlq() {
		return codDetAlq;
	}

	public void setCodDetAlq(Integer codDetAlq) {
		this.codDetAlq = codDetAlq;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Manga getADetalleManga() {
		return ADetalleManga;
	}

	public void setADetalleManga(Manga aDetalleManga) {
		ADetalleManga = aDetalleManga;
	}

	public Alquiler getADetalleAlquiler() {
		return ADetalleAlquiler;
	}

	public void setADetalleAlquiler(Alquiler aDetalleAlquiler) {
		ADetalleAlquiler = aDetalleAlquiler;
	}

	public AlquilerDetalle(Integer codDetAlq, BigDecimal precio, int cantidad, Manga aDetalleManga,
			Alquiler aDetalleAlquiler) {
		super();
		this.codDetAlq = codDetAlq;
		this.precio = precio;
		this.cantidad = cantidad;
		ADetalleManga = aDetalleManga;
		ADetalleAlquiler = aDetalleAlquiler;
	}

	public AlquilerDetalle() {
		super();
	} 
}
