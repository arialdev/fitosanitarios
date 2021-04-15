package es.codeurjc.fitosanitarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.fitosanitarios.models.Producto;
import es.codeurjc.fitosanitarios.repositories.ProductoRepository;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@RequestMapping("/productos")
	public String board(Model model) {
		List<Producto> productos = productoRepository.findAll();
		model.addAttribute("productos", productos);
		return "productos";
	}
	
	@RequestMapping("/producto/view/{id}")
	public String viewProducto(@PathVariable Long id, Model model) {
		Optional<Producto> producto = productoRepository.findById(id);
		if (producto.isPresent()) {
			model.addAttribute("producto", producto.get());
			return "producto-detail-view";
		}
		return "error";
	}
	
	@RequestMapping("/producto/update/{id}")
	public String modifyProducto(@PathVariable Long id, Model model) {
		Optional<Producto> producto = productoRepository.findById(id);
		if (producto.isPresent()) {
			model.addAttribute("producto", producto.get());
			return "producto-detail-modify";
		}
		return "error";
	}

	@RequestMapping("/producto/update/save/{id}")
	public String updateProducto(@PathVariable Long id, Model model, Producto updatedProducto) {
		Optional<Producto> producto = productoRepository.findById(id);
		if (producto.isPresent()) {
			productoRepository.save(producto.get().update(updatedProducto));
			return "producto/view/" + id;
		}
		return "error";
	}
	
	@RequestMapping("/producto/delete/{id}")
	public String deleteProducto(@PathVariable Long id, Model model) {
		Optional<Producto> producto = productoRepository.findById(id);
		if (producto.isPresent()) {
			productoRepository.delete(producto.get());
			return "productos";
		}
		return "error";
	}

	@RequestMapping("/producto/new")
	public String newProducto(Model model) {
		return "producto-new";
	}

	@RequestMapping("/producto/new/save")
	public String newProducto(Model model, Producto newProducto) {
		productoRepository.save(newProducto);
		return "productos";
	}
}
