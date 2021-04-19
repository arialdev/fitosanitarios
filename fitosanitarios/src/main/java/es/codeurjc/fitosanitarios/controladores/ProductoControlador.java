package es.codeurjc.fitosanitarios.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.fitosanitarios.modelos.Producto;
import es.codeurjc.fitosanitarios.repositorios.ProductoRepositorio;

@Controller
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@RequestMapping("/productos")
	public String vista(Model modelo) {
		List<Producto> productos = productoRepositorio.findAll();
		modelo.addAttribute("productos", productos);
		return "productos";
	}
	
	@RequestMapping("/producto/detalle/{id}")
	public String detalleProducto(@PathVariable Long id, Model model) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		if (producto.isPresent()) {
			model.addAttribute("producto", producto.get());
			return "producto-detalle";
		}
		return "error";
	}
	
	@RequestMapping("/producto/modificacion/{id}")
	public String modificacionProducto(@PathVariable Long id, Model modelo) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		if (producto.isPresent()) {
			modelo.addAttribute("producto", producto.get());
			return "producto-modificacion";
		}
		return "error";
	}

	@RequestMapping("/producto/modificacion/guardado/{id}")
	public String updateProducto(@PathVariable Long id, Model modelo, Producto productoModificado) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		if (producto.isPresent()) {
			productoRepositorio.save(producto.get().actualizar(productoModificado));
			return vista(modelo);
		}
		return "error";
	}
	
	@RequestMapping("/producto/borrado/{id}")
	public String borradoProducto(@PathVariable Long id, Model modelo) {
		Optional<Producto> producto = productoRepositorio.findById(id);
		if (producto.isPresent()) {
			productoRepositorio.delete(producto.get());
			return vista(modelo);
		}
		return "error";
	}

	@RequestMapping("/producto/nuevo")
	public String nuevoProducto(Model modelo) {
		return "producto-nuevo";
	}

	@RequestMapping("/producto/nuevo/guardado")
	public String nuevoProducto(Model modelo, Producto productoNuevo) {
		productoRepositorio.save(productoNuevo);
		return vista(modelo);
	}
}
