package es.codeurjc.fitosanitarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.codeurjc.fitosanitarios.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
