package es.codeurjc.fitosanitarios.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.fitosanitarios.models.*;
import es.codeurjc.fitosanitarios.repositories.CultivoRepository;
import es.codeurjc.fitosanitarios.repositories.ProductoRepository;
import es.codeurjc.fitosanitarios.repositories.TratamientoRepository;

@Controller
public class TratamientoController {

	@Autowired
	private CultivoRepository cultivoRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TratamientoRepository tratamientoRepository;
	
	@PostConstruct
	public void init() {
		
		Producto producto1 = new Producto("Producto 1", "Descripcion 1", 15, 30);
		Producto producto2 = new Producto("Producto 2", "Descripcion 2", 20, 35);
		Producto producto3 = new Producto("Producto 3", "Descripcion 3", 25, 40);
		Producto producto4 = new Producto("Producto 4", "Descripcion 4", 15, 0);
		Producto producto5 = new Producto("Producto 5", "Descripcion 5", 0, 15);
		Producto producto6 = new Producto("Producto 6", "Descripcion 6", 0, 0);
		
		productoRepository.saveAll(Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6));
		
		Cultivo cultivo1 = new Cultivo("Especie 1", "Variedad 1", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo2 = new Cultivo("Especie 1", "Variedad 1", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo3 = new Cultivo("Especie 1", "Variedad 1", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo4 = new Cultivo("Especie 2", "Variedad 2", LocalDate.now().plusDays(1), "Alicante", new LinkedList<>());
		Cultivo cultivo5 = new Cultivo("Especie 2", "Variedad 2", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo6 = new Cultivo("Especie 2", "Variedad 2", LocalDate.now(), "", new LinkedList<>());
		
		cultivoRepository.saveAll(Arrays.asList(cultivo1, cultivo2, cultivo3, cultivo4, cultivo5, cultivo6));
		
		Tratamiento tratamiento1 = new Tratamiento(cultivo2, producto6, "lote 1", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento2 = new Tratamiento(cultivo3, producto5, "lote 2", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento3 = new Tratamiento(cultivo4, producto1, "lote 3", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		
		Tratamiento tratamiento4 = new Tratamiento(cultivo5, producto1, "lote 4", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento5 = new Tratamiento(cultivo5, producto2, "lote 4", LocalDate.now().plusDays(10), LocalDate.now().plusDays(11), LocalDate.now().plusDays(12));
		
		Tratamiento tratamiento6 = new Tratamiento(cultivo6, producto5, "lote 5", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento7 = new Tratamiento(cultivo6, producto1, "lote 5", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento8 = new Tratamiento(cultivo6, producto2, "lote 5", LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

		tratamientoRepository.saveAll(Arrays.asList(tratamiento1, tratamiento2, tratamiento3, tratamiento4, tratamiento5, tratamiento6, tratamiento7, tratamiento8));
		
		cultivo2.getTratamientos().add(tratamiento1);
		cultivo3.getTratamientos().add(tratamiento2);
		cultivo4.getTratamientos().add(tratamiento3);
		cultivo5.getTratamientos().addAll(Arrays.asList(tratamiento4, tratamiento5));
		cultivo6.getTratamientos().addAll(Arrays.asList(tratamiento6, tratamiento7, tratamiento8));
		
		cultivoRepository.saveAll(Arrays.asList(cultivo2, cultivo3, cultivo4, cultivo5, cultivo6));
	}

	@RequestMapping("/tratamientos")
	public String board(Model model) {
		List<Tratamiento> tratamientos = tratamientoRepository.findAll();
		model.addAttribute("tratamientos", tratamientos);
		return "tratamientos";
	}
	
	@RequestMapping("/tratamiento/view/{id}")
	public String viewTratamiento(@PathVariable Long id, Model model) {
		Optional<Tratamiento> tratamiento = tratamientoRepository.findById(id);
		if (tratamiento.isPresent()) {
			model.addAttribute("tratamiento", tratamiento.get());
			return "tratamiento-detail-view";
		}
		return "error";
	}
	
	@RequestMapping("/tratamiento/update/{id}")
	public String modifyTratamiento(@PathVariable Long id, Model model) {
		Optional<Tratamiento> tratamiento = tratamientoRepository.findById(id);
		if (tratamiento.isPresent()) {
			model.addAttribute("tratamiento", tratamiento.get());
			return "tratamiento-detail-modify";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/update/save/{id}")
	public String updateTratamiento(@PathVariable Long id, Model model, Tratamiento updatedTratamiento) {
		Optional<Tratamiento> tratamiento = tratamientoRepository.findById(id);
		if (tratamiento.isPresent()) {
			tratamientoRepository.save(tratamiento.get().update(updatedTratamiento));
			return "tratamiento/view/" + id;
		}
		return "error";
	}
	
	@RequestMapping("/tratamiento/delete/{id}")
	public String deleteTratamiento(@PathVariable Long id, Model model) {
		Optional<Tratamiento> tratamiento = tratamientoRepository.findById(id);
		if (tratamiento.isPresent()) {
			tratamientoRepository.delete(tratamiento.get());
			return "tratamientos";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/new")
	public String newTratamiento(Model model) {
		return "tratamiento-new";
	}

	@RequestMapping("/tratamiento/new/save")
	public String newTratamiento(Model model, Tratamiento newTratamiento) {
		tratamientoRepository.save(newTratamiento);
		return "tratamientos";
	}
}
