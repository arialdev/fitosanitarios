package es.codeurjc.fitosanitarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.fitosanitarios.modelos.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}
