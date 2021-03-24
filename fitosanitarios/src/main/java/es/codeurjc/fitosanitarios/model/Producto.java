package es.codeurjc.fitosanitarios.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;

	private String descripcion;

	private int plazoReentrada;

	private int plazoRecoleccion;

	public Producto() {

	}

	public Producto(String nombre, String descripcion, int plazoReentrada, int plazoRecoleccion,
			Tratamiento tratamiento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plazoReentrada = plazoReentrada;
		this.plazoRecoleccion = plazoRecoleccion;
		this.tratamiento = tratamiento;
	}

	@OneToOne
	private Tratamiento tratamiento;

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPlazoReentrada() {
		return plazoReentrada;
	}

	public void setPlazoReentrada(int plazoReentrada) {
		this.plazoReentrada = plazoReentrada;
	}

	public int getPlazoRecoleccion() {
		return plazoRecoleccion;
	}

	public void setPlazoRecoleccion(int plazoRecoleccion) {
		this.plazoRecoleccion = plazoRecoleccion;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

}
