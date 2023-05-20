package com.smanga.proyecto.entity;

import java.math.BigDecimal;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_alquiler")
public class Alquiler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_alq")
	private Integer codAlq;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fec_alq")
	private Date fecAlq;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fec_dev")
	private Date fecDev;
	
	@Column(name = "monto_alq")
	private BigDecimal montoAlq;
	
	@Column(name = "estado")
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "cod_usu") 
	private Usuario alquilerUsuario; 
	
	@ManyToOne
	@JoinColumn(name = "cod_adm") 
	private Administrador alquilerAdministrador;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ADetalleAlquiler")
	private List<AlquilerDetalle> listaADetalle;

	public Integer getCodAlq() {
		return codAlq;
	}

	public void setCodAlq(Integer codAlq) {
		this.codAlq = codAlq;
	}

	public Date getFecAlq() {
		return fecAlq;
	}

	public void setFecAlq(Date fecAlq) {
		this.fecAlq = fecAlq;
	}

	public Date getFecDev() {
		return fecDev;
	}

	public void setFecDev(Date fecDev) {
		this.fecDev = fecDev;
	}

	public BigDecimal getMontoAlq() {
		return montoAlq;
	}

	public void setMontoAlq(BigDecimal montoAlq) {
		this.montoAlq = montoAlq;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getAlquilerUsuario() {
		return alquilerUsuario;
	}

	public void setAlquilerUsuario(Usuario alquilerUsuario) {
		this.alquilerUsuario = alquilerUsuario;
	}

	public Administrador getAlquilerAdministrador() {
		return alquilerAdministrador;
	}

	public void setAlquilerAdministrador(Administrador alquilerAdministrador) {
		this.alquilerAdministrador = alquilerAdministrador;
	}

	public List<AlquilerDetalle> getListaADetalle() {
		return listaADetalle;
	}

	public void setListaADetalle(List<AlquilerDetalle> listaADetalle) {
		this.listaADetalle = listaADetalle;
	}

	public Alquiler(Integer codAlq, Date fecAlq, Date fecDev, BigDecimal montoAlq, String estado,
			Usuario alquilerUsuario, Administrador alquilerAdministrador, List<AlquilerDetalle> listaADetalle) {
		super();
		this.codAlq = codAlq;
		this.fecAlq = fecAlq;
		this.fecDev = fecDev;
		this.montoAlq = montoAlq;
		this.estado = estado;
		this.alquilerUsuario = alquilerUsuario;
		this.alquilerAdministrador = alquilerAdministrador;
		this.listaADetalle = listaADetalle;
	}

	public Alquiler() {
		super();
	}
}
