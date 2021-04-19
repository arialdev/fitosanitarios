package es.codeurjc.fitosanitarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.fitosanitarios.modelos.Cultivo;

public interface CultivoRepositorio extends JpaRepository<Cultivo, Long> {

}
