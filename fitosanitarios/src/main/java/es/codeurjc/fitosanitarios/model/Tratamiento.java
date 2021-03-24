package es.codeurjc.fitosanitarios.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Tratamiento {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Cultivo cultivo;
	
	@OneToOne
	private Producto producto;
	
	private String lote;
	
	private LocalDate fecha_aplicacion;
	
	private LocalDate fecha_reentrada;
	
	private LocalDate fecha_recoleccion;
	

	public Tratamiento() {
	}

	public Tratamiento(Cultivo cultivo, Producto producto, String lote, LocalDate fecha_aplicacion,
			LocalDate fecha_reentrada, LocalDate fecha_recoleccion) {
		super();
		this.cultivo = cultivo;
		this.producto = producto;
		this.lote = lote;
		this.fecha_aplicacion = fecha_aplicacion;
		this.fecha_reentrada = fecha_reentrada;
		this.fecha_recoleccion = fecha_recoleccion;
	}

	public long getId() {
		return id;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public LocalDate getFecha_aplicacion() {
		return fecha_aplicacion;
	}

	public void setFecha_aplicacion(LocalDate fecha_aplicacion) {
		this.fecha_aplicacion = fecha_aplicacion;
	}

	public LocalDate getFecha_reentrada() {
		return fecha_reentrada;
	}

	public void setFecha_reentrada(LocalDate fecha_reentrada) {
		this.fecha_reentrada = fecha_reentrada;
	}

	public LocalDate getFecha_recoleccion() {
		return fecha_recoleccion;
	}

	public void setFecha_recoleccion(LocalDate fecha_recoleccion) {
		this.fecha_recoleccion = fecha_recoleccion;
	}	
}
