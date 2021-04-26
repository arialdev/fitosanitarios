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

		Producto producto1 = new Producto("Producto 1", "Descripcion 1", 15, 30);
		Producto producto2 = new Producto("Producto 2", "Descripcion 2", 20, 35);
		Producto producto3 = new Producto("Producto 3", "Descripcion 3", 25, 40);
		Producto producto4 = new Producto("Producto 4", "Descripcion 4", 15, 0);
		Producto producto5 = new Producto("Producto 5", "Descripcion 5", 0, 15);
		Producto producto6 = new Producto("Producto 6", "Descripcion 6", 0, 0);

		productoRepositorio.saveAll(Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6));

		Cultivo cultivo1 = new Cultivo("Especie 2", "Variedad 1", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo2 = new Cultivo("Especie 2", "Variedad 1", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo3 = new Cultivo("Especie 2", "Variedad 1", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo4 = new Cultivo("Especie 1", "Variedad 2", LocalDate.now().plusDays(1), "Alicante",
				new LinkedList<>());
		Cultivo cultivo5 = new Cultivo("Especie 1", "Variedad 2", LocalDate.now(), "", new LinkedList<>());
		Cultivo cultivo6 = new Cultivo("Especie 1", "Variedad 2", LocalDate.now(), "", new LinkedList<>());

		cultivoRepositorio.saveAll(Arrays.asList(cultivo1, cultivo2, cultivo3, cultivo4, cultivo5, cultivo6));

		Tratamiento tratamiento1 = new Tratamiento(cultivo2, producto6, "lote 1", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento2 = new Tratamiento(cultivo3, producto5, "lote 2", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento3 = new Tratamiento(cultivo4, producto1, "lote 3", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));

		Tratamiento tratamiento4 = new Tratamiento(cultivo5, producto1, "lote 4", LocalDate.now(),
				LocalDate.now().plusDays(10), LocalDate.now().plusDays(21));
		Tratamiento tratamiento5 = new Tratamiento(cultivo5, producto2, "lote 4", LocalDate.now().plusDays(10),
				LocalDate.now().plusDays(11), LocalDate.now().plusDays(12));

		Tratamiento tratamiento6 = new Tratamiento(cultivo6, producto5, "lote 5", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento7 = new Tratamiento(cultivo6, producto1, "lote 5", LocalDate.now(),
				LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
		Tratamiento tratamiento8 = new Tratamiento(cultivo6, producto2, "lote 5", LocalDate.now(),
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
		tratamientos.sort((tratamiento1, tratamiento2) -> tratamiento1.getCultivo().getEspecie()
				.compareTo(tratamiento2.getCultivo().getEspecie()));
		modelo.addAttribute("tratamientos", tratamientos);
		modelo.addAttribute("ordenEspecie", true);
		return "tratamientos";
	}

	@RequestMapping("/tratamientos/fechaReentrada")
	public String vistaFechaReentrada(Model modelo) {
		List<Tratamiento> tratamientos = tratamientoRepositorio.findAll();
		tratamientos.sort((tratamiento1, tratamiento2) -> tratamiento1.getFechaReentrada()
				.compareTo(tratamiento2.getFechaReentrada()));
		modelo.addAttribute("tratamientos", tratamientos);
		modelo.addAttribute("ordenReentrada", true);
		return "tratamientos";
	}

	@RequestMapping("/tratamientos/fechaRecoleccion")
	public String vistaFechaRecoleccion(Model modelo) {
		List<Tratamiento> tratamientos = tratamientoRepositorio.findAll();
		tratamientos.sort((tratamiento1, tratamiento2) -> tratamiento1.getFechaRecoleccion()
				.compareTo(tratamiento2.getFechaRecoleccion()));
		modelo.addAttribute("tratamientos", tratamientos);
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

	@RequestMapping("/tratamiento/detalle/{id}")
	public String detalleTratamiento(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			modelo.addAttribute("tratamiento", tratamiento.get());
			return "tratamiento-detalle";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/modificacion/{id}")
	public String modificacion(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			modelo.addAttribute("tratamiento", tratamiento.get());
			return "tratamiento-modificacion";
		}
		return "error";
	}

	@RequestMapping("/tratamiento/modificacion/guardado/{id}")
	public String modificacionGuardadoTratamiento(@PathVariable Long id, Model modelo,
			Tratamiento tratamientoModificado) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			tratamientoRepositorio.save(tratamiento.get().actualizar(tratamientoModificado));
			return vista(modelo);
		}
		return "error";
	}

	@RequestMapping("/tratamiento/borrado/{id}")
	public String borradoTratamiento(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);
		if (tratamiento.isPresent()) {
			tratamientoRepositorio.delete(tratamiento.get());
			return vista(modelo);
		}
		return "error";
	}

	@RequestMapping("/tratamiento/nuevo")
	public String nuevoTratamiento(Model modelo) {
		return "tratamiento-nuevo";
	}

	@RequestMapping("/tratamiento/nuevo/guardado")
	public String nuevoTratamiento(Model modelo, @RequestParam Long cultivoID, @RequestParam Long productoID, @RequestParam String lote, @RequestParam LocalDate fAplicacion) {
		Cultivo caux = new Cultivo();
		caux = cultivoRepositorio.findById(cultivoID).get();
		Producto paux = new Producto();
		paux = productoRepositorio.findById(productoID).get();
		
		Tratamiento nuevoTratamiento = new Tratamiento(caux, paux, lote, fAplicacion,fAplicacion.plusDays(paux.getPlazoReentrada()), fAplicacion.plusDays(paux.getPlazoRecoleccion()));
		tratamientoRepositorio.save(nuevoTratamiento);
		return vista(modelo);
	}
}
