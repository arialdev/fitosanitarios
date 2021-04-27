package es.codeurjc.fitosanitarios.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.fitosanitarios.modelos.Cultivo;
import es.codeurjc.fitosanitarios.modelos.Tratamiento;
import es.codeurjc.fitosanitarios.repositorios.CultivoRepositorio;
import es.codeurjc.fitosanitarios.repositorios.ProductoRepositorio;
import es.codeurjc.fitosanitarios.repositorios.TratamientoRepositorio;

@Controller
public class CultivoControlador {

	@Autowired
	private CultivoRepositorio cultivoRepositorio;
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private TratamientoRepositorio tratamientoRepositorio; 


	@RequestMapping("/cultivos")
	public String vista(Model modelo) {
		List<Cultivo> cultivos = cultivoRepositorio.findAll();
		modelo.addAttribute("cultivos", cultivos);
		return "cultivos";
	}

	@RequestMapping("/cultivo/detalle/{id}")
	public String detalleCultivo(@PathVariable Long id, Model modelo) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			modelo.addAttribute("cultivo", cultivo.get());
			modelo.addAttribute("listadoTratamientosInfo", ((cultivo.get().getTratamientos().isEmpty()))? "Sin tratamientos asociados" : "Listado de tratamientos:");
			return "cultivo-detalle";
		}
		return "error";
	}

	@RequestMapping("/cultivo/modificacion/{id}")
	public String modificacionCultivo(@PathVariable Long id, Model modelo) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			modelo.addAttribute("cultivo", cultivo.get());
			return "cultivo-modificacion";
		}
		return "error";
	}
	
	@RequestMapping("/cultivo/borrado/tratamiento/{id}")
	public String borradoTratamientoCultivo(@PathVariable Long id, Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(id);		
		if (tratamiento.isPresent()) {
			modelo.addAttribute("cultivo", tratamiento.get().getCultivo());
			tratamientoRepositorio.delete(tratamiento.get());
			return vista(modelo);
		}
		return "error";
	}

	@RequestMapping("/cultivo/modificacion/guardado/{id}")
	public String modificacionCultivo(@PathVariable Long id, Model modelo, Cultivo cultivoModificado) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			cultivoRepositorio.save(cultivo.get().actualizar(cultivoModificado));
			return vista(modelo);
		}
		return "error";
	}

	@RequestMapping("/cultivo/borrado/{id}")
	public String borradoCultivo(@PathVariable Long id, Model modelo) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			cultivoRepositorio.delete(cultivo.get());
			return vista(modelo);
		}
		return "error";
	}
	
	//esto es lo que hemos añadido que es un copy-paste del tratamiento controlador solo que este envía true
	@RequestMapping("/cultivo/nuevo/tratamiento")
	public String nuevoTratamiento(Model modelo) {
		modelo.addAttribute("cultivos", cultivoRepositorio.findAll());
		modelo.addAttribute("productos", productoRepositorio.findAll());
		modelo.addAttribute("vengoDeCultivo", true);
		return "tratamiento-nuevo";
	}

	@RequestMapping("/cultivo/nuevo")
	public String nuevoCultivo(Model modelo) {
		return "cultivo-nuevo";
	}

	@RequestMapping("/cultivo/nuevo/guardado")
	public String nuevoCultivo(Model modelo, Cultivo cultivoNuevo) {
		cultivoRepositorio.save(cultivoNuevo);
		return vista(modelo);
	}
}
