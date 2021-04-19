package es.codeurjc.fitosanitarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.fitosanitarios.modelos.Tratamiento;

public interface TratamientoRepositorio extends JpaRepository<Tratamiento, Long> {

}
