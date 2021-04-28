package es.codeurjc.fitosanitarios.controladores;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.fitosanitarios.modelos.*;
import es.codeurjc.fitosanitarios.repositorios.CultivoRepositorio;
import es.codeurjc.fitosanitarios.repositorios.ProductoRepositorio;
import es.codeurjc.fitosanitarios.repositorios.TratamientoRepositorio;

@Controller
public class TratamientoControlador {

	@Autowired
	private CultivoRepositorio cultivoRepositorio;

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Autowired
	private TratamientoRepositorio tratamientoRepositorio;

	@PostConstruct
	public void init() {

		Producto producto1 = new Producto("Insecticida", "Válido contra todo tipo de insectos", 15, 30);
		Producto producto2 = new Producto("Fungicida", "Efectivo contra musgos", 20, 35);
		Producto producto3 = new Producto("Nitrato", "Para nitrogenar la tierra", 25, 40);
		Producto producto4 = new Producto("Anti-topos", "No queda ni uno", 15, 20);
		Producto producto5 = new Producto("Mata-ratas", "Consultar fecha de caducidad", 0, 15);
		Producto producto6 = new Producto("Abono", "Origen porcino", 0, 0);

		productoRepositorio.saveAll(Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6));

		Cultivo cultivo1 = new Cultivo("Naranja", "Navel", LocalDate.now(), "La Solana", new LinkedList<>());
		Cultivo cultivo2 = new Cultivo("Patata", "Monalisa", LocalDate.now(), "Los Cerrillos", new LinkedList<>());
		Cultivo cultivo3 = new Cultivo("Naranja", "Blanca", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo4 = new Cultivo("Judía", "Pardiña", LocalDate.now().plusDays(1), "Los Yesares",
				new LinkedList<>());
		Cultivo cultivo5 = new Cultivo("Patata", "Red pontiac", LocalDate.now(), "Los Terreros", new LinkedList<>());
		Cultivo cultivo6 = new Cultivo("Judía", "Colorada", LocalDate.now(), "La Cañada", new LinkedList<>());

		cultivoRepositorio.saveAll(Arrays.asList(cultivo1, cultivo2, cultivo3, cultivo4, cultivo5, cultivo6));

		Tratamiento tratamiento1 = new Tratamiento(cultivo2, producto6, "472/z4", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento2 = new Tratamiento(cultivo3, producto5, "158/x3", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento3 = new Tratamiento(cultivo4, producto1, "875/z6", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

		Tratamiento tratamiento4 = new Tratamiento(cultivo5, producto1, "452/f8", LocalDate.now(),
				LocalDate.now().plusDays(10), LocalDate.now().plusDays(21));
		Tratamiento tratamiento5 = new Tratamiento(cultivo5, producto2, "993/h1", LocalDate.now().plusDays(10),
				LocalDate.now().plusDays(11), LocalDate.now().plusDays(12));

		Tratamiento tratamiento6 = new Tratamiento(cultivo6, producto5, "812/f4", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento7 = new Tratamiento(cultivo6, producto1, "873/p1", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento8 = new Tratamiento(cultivo6, producto2, "564/u7", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

		tratamientoRepositorio.saveAll(Arrays.asList(tratamiento1, tratamiento2, tratamiento3, tratamiento4,
				tratamiento5, tratamiento6, tratamiento7, tratamiento8));

		cultivo2.getTratamientos().add(tratamiento1);
		cultivo3.getTratamientos().add(tratamiento2);
		cultivo4.getTratamientos().add(tratamiento3);
		cultivo5.getTratamientos().addAll(Arrays.asList(tratamiento4, tratamiento5));
		cultivo6.getTratamientos().addAll(Arrays.asList(tratamiento6, tratamiento7, tratamiento8));
		
		cultivoRepositorio.saveAll(Arrays.asList(cultivo2, cultivo3, cultivo4, cultivo5, cultivo6));
	}


	@RequestMapping("/tratamientos")
	public String vista(Model modelo) {
		List<Tratamiento> tratamientos = tratamientoRepositorio.findAll();
		modelo.addAttribute("tratamientos", tratamientos);
		return "tratamientos";
	}

	@RequestMapping("/tratamientos/especie")
	public String vistaEspecie(Model modelo) {
		List<Tratamiento> tratamientos = tratamientoRepositorio.findAll();
		tratamientos = tratamientoRepositorio.findByOrderByCultivo_EspecieAsc();
		modelo.addAttribute("tratamientos", tratamientos);
		modelo.addAttribute("ordenEspecie", true);
		return "tratamientos";
	}

	@RequestMapping("/tratamientos/fechaReentrada")
	public String vistaFechaReentrada(Model modelo) {
		modelo.addAttribute("tratamientos", tratamientoRepositorio.findByOrderByFechaReentradaAsc());
		modelo.addAttribute("ordenReentrada", true);
		return "tratamientos";
	}

	@RequestMapping("/tratamientos/fechaRecoleccion")
	public String vistaFechaRecoleccion(Model modelo) {
		modelo.addAttribute("tratamientos", tratamientoRepositorio.findByOrderByFechaRecoleccionAsc());
		modelo.addAttribute("ordenRecoleccion", true);
		return "tratamientos";
	}

	@RequestMapping("/tratamientos/filtrado/{plazoSeguridad}")
	public String filtrarTratamiento(@PathVariable LocalDate plazoSeguridad, Model modelo) {
		List<Tratamiento> tratamientos = tratamientoRepositorio.findAll();
		modelo.addAttribute("tratamientos",
				tratamientos.stream()
						.filter(tramiento -> (tramiento.getFechaRecoleccion().compareTo(plazoSeguridad) > 0
								|| tramiento.getFechaReentrada().compareTo(plazoSeguridad) > 0))
						.collect(Collectors.toList()));
		modelo.addAttribute("fechaPlazo", plazoSeguridad);
		return "tratamientos";
	}

	@RequestMapping("/tratamiento/{id}/detalle")
	public String detalleTratamiento(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			modelo.addAttribute("tratamiento", tratamiento.get());
			return "tratamiento-detalle";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/{id}/modificacion")
	public String modificacion(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			modelo.addAttribute("tratamiento", tratamiento.get());
			modelo.addAttribute("cultivos", cultivoRepositorio.findAll());
			modelo.addAttribute("productos", productoRepositorio.findAll());
			return "tratamiento-modificacion";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/{id}/modificacion/guardado")
	public String modificacionGuardadoTratamiento(@PathVariable Long id, Model modelo, @RequestParam Cultivo cultivo, @RequestParam Producto producto, 
			@RequestParam String lote, @RequestParam LocalDate fechaAplicacion, @RequestParam LocalDate fechaRecoleccion, @RequestParam LocalDate fechaReentrada) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			Tratamiento tratamientoModificado = new Tratamiento(cultivo, producto, lote, fechaAplicacion, fechaReentrada, fechaRecoleccion);
			tratamientoRepositorio.save(tratamiento.get().actualizar(tratamientoModificado));
			return "redirect:/tratamientos";
		}
		return "error";
	}
	

	@RequestMapping("/tratamiento/{id}/borrado")
	public String borradoTratamiento(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			tratamientoRepositorio.delete(tratamiento.get());
			return "redirect:/tratamientos";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/nuevo")
	public String nuevoTratamiento(Model modelo) {
		modelo.addAttribute("cultivos", cultivoRepositorio.findAll());
		modelo.addAttribute("productos", productoRepositorio.findAll());
		modelo.addAttribute("origenCultivoId", -1);
		return "tratamiento-nuevo";
	}

	@RequestMapping("/tratamiento/nuevo/guardado")
	public String nuevoTratamiento(Model modelo, @RequestParam Cultivo cultivo, @RequestParam Producto producto, @RequestParam String lote, @RequestParam LocalDate fechaAplicacion, @RequestParam String origenCultivoId) {
		Tratamiento nuevoTratamiento = new Tratamiento(cultivo, producto, lote, fechaAplicacion, fechaAplicacion.plusDays(producto.getPlazoReentrada()), fechaAplicacion.plusDays(producto.getPlazoRecoleccion()));
		tratamientoRepositorio.save(nuevoTratamiento);
		return (!origenCultivoId.equals("-1"))? "redirect:/cultivo/" + Long.parseLong(origenCultivoId) + "/modificacion" : "redirect:/tratamientos";
	}
}
