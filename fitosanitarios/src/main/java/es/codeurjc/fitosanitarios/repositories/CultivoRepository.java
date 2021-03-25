package es.codeurjc.fitosanitarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.codeurjc.fitosanitarios.models.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Long> {

}
