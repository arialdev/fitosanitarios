package es.codeurjc.fitosanitarios.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.fitosanitarios.modelos.Tratamiento;

public interface TratamientoRepositorio extends JpaRepository<Tratamiento, Long> {
	
	List<Tratamiento> findByOrderByFechaReentradaAsc();
	List<Tratamiento> findByFechaRecoleccionGreaterThanOrderByFechasReentradaAsc(LocalDate fechaRecollecion);
	
	List<Tratamiento> findByOrderByFechaRecoleccionAsc();
	List<Tratamiento> findByFechaRecoleccionGreaterThanOrderByFechasRecoleccionAsc(LocalDate fechaRecollecion);
	
	List<Tratamiento> findByOrderByCultivo_EspecieAsc();
	List<Tratamiento> findByFechaRecoleccionGreaterThanOrderByCultiva_EspecieAsc(LocalDate fechaRecollecion);
	
	List<Tratamiento> findByFechaRecoleccionGreaterThan(LocalDate fechaRecoleccion);

}
