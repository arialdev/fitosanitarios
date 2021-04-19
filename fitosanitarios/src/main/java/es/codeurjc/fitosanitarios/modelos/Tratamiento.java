package es.codeurjc.fitosanitarios.modelos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tratamiento {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Cultivo cultivo;
	
	@ManyToOne
	private Producto producto;
	
	private String lote;
	
	private LocalDate fechaAplicacion;
	
	private LocalDate fechaReentrada;
	
	private LocalDate fechaRecoleccion;
	

	public Tratamiento() {
	}

	public Tratamiento(Cultivo cultivo, Producto producto, String lote, LocalDate fechaAplicacion, LocalDate fechaReentrada, LocalDate fechaRecoleccion) {
		this.cultivo = cultivo;
		this.producto = producto;
		this.lote = lote;
		this.fechaAplicacion = fechaAplicacion;
		this.fechaReentrada = fechaReentrada;
		this.fechaRecoleccion = fechaRecoleccion;
	}
	
	public Tratamiento actualizar (Tratamiento tratamientoModificado) {
		this.cultivo = tratamientoModificado.getCultivo();
		this.producto = tratamientoModificado.getProducto();
		this.lote = tratamientoModificado.getLote();
		this.fechaAplicacion = tratamientoModificado.getFechaAplicacion();
		this.fechaReentrada = tratamientoModificado.getFechaReentrada();
		this.fechaRecoleccion = tratamientoModificado.getFechaRecoleccion();
		return this;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	public LocalDate getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(LocalDate fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public LocalDate getFechaReentrada() {
		return fechaReentrada;
	}

	public void setFechaReentrada(LocalDate fechaReentrada) {
		this.fechaReentrada = fechaReentrada;
	}

	public LocalDate getFechaRecoleccion() {
		return fechaRecoleccion;
	}

	public void setFechaRecoleccion(LocalDate fechaRecoleccion) {
		this.fechaRecoleccion = fechaRecoleccion;
	}	
}
