package es.codeurjc.fitosanitarios.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.fitosanitarios.modelos.Cultivo;
import es.codeurjc.fitosanitarios.repositorios.CultivoRepositorio;

@Controller
public class CultivoControlador {

	@Autowired
	private CultivoRepositorio cultivoRepositorio;

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
