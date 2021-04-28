package es.codeurjc.fitosanitarios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.fitosanitarios.modelos.Tratamiento;

public interface TratamientoRepositorio extends JpaRepository<Tratamiento, Long> {
	
	List<Tratamiento> findByOrderByFechaReentradaAsc();
	
	List<Tratamiento> findByOrderByFechaRecoleccionAsc();
	
	List<Tratamiento> findByOrderByCultivo_EspecieAsc();

}
