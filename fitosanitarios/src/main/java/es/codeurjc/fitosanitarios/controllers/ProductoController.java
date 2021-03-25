package es.codeurjc.fitosanitarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "productos-view";
	}
}
