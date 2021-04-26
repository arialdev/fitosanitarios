package es.codeurjc.fitosanitarios.modelos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Cultivo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String especie;
	private String variedad;
	
	private LocalDate fechaPlantado;
	private String zona;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="cultivo")
	private List<Tratamiento> tratamientos;

	public Cultivo() {
		
	}
	
	public Cultivo(String especie, String variedad, LocalDate fecha_plantado, String zona, List<Tratamiento> tratamientos) {
		this.especie = especie;
		this.variedad = variedad;
		this.fechaPlantado = fecha_plantado;
		this.zona = zona;
		this.tratamientos = tratamientos;
	}
	
	public Cultivo actualizar(Cultivo cultivoModificado) {
		this.especie = cultivoModificado.getEspecie();
		this.variedad = cultivoModificado.getVariedad();
		this.fechaPlantado = cultivoModificado.getFechaPlantado();
		this.zona = cultivoModificado.getZona();
		this.tratamientos = cultivoModificado.getTratamientos();
		return this;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public LocalDate getFechaPlantado() {
		return fechaPlantado;
	}

	public void setFechaPlantado(LocalDate fechaPlantado) {
		this.fechaPlantado = fechaPlantado;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}
	
}
