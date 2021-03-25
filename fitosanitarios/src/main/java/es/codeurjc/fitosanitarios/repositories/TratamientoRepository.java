package es.codeurjc.fitosanitarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.codeurjc.fitosanitarios.models.Tratamiento;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

}
