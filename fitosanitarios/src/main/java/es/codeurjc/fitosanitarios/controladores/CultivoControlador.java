package es.codeurjc.fitosanitarios.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/cultivo/{id}/detalle")
	public String detalleCultivo(@PathVariable Long id, Model modelo) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			modelo.addAttribute("cultivo", cultivo.get());
			return "cultivo-detalle";
		}
		return "error";
	}

	@RequestMapping("/cultivo/{id}/modificacion")
	public String modificacionCultivo(@PathVariable Long id, Model modelo) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			modelo.addAttribute("cultivo", cultivo.get());
			return "cultivo-modificacion";
		}
		return "error";
	}

	@RequestMapping("/cultivo/{id}/modificacion/guardado")
	public String modificacionCultivo(@PathVariable Long id, Model modelo, Cultivo cultivoModificado) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			cultivoRepositorio.save(cultivo.get().actualizar(cultivoModificado));
			return "redirect:/cultivos";
		}
		return "error";
	}

	@RequestMapping("/cultivo/{id}/borrado")
	public String borradoCultivo(@PathVariable Long id, Model modelo) {
		Optional<Cultivo> cultivo = cultivoRepositorio.findById(id);
		if (cultivo.isPresent()) {
			cultivoRepositorio.delete(cultivo.get());
			return "redirect:/cultivos";
		}
		return "error";
	}

	@RequestMapping("/cultivo/{cultivoId}/borrado/tratamiento/{tratamientoId}")
	public String borradoTratamientoCultivo(@PathVariable Long cultivoId, @PathVariable Long tratamientoId,
			Model modelo) {
		Optional<Tratamiento> tratamiento = tratamientoRepositorio.findById(tratamientoId);
		if (tratamiento.isPresent()) {
			modelo.addAttribute("cultivo", tratamiento.get().getCultivo());
			tratamientoRepositorio.delete(tratamiento.get());
			return "redirect:/cultivo/{cultivoId}/modificacion";
		}
		return "error";
	}

	@RequestMapping("/cultivo/{id}/nuevo/tratamiento")
	public String nuevoTratamiento(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("cultivos", cultivoRepositorio.findAll());
		modelo.addAttribute("productos", productoRepositorio.findAll());
		modelo.addAttribute("origenCultivoId", id);
		return "tratamiento-nuevo";
	}

	@RequestMapping("/cultivo/nuevo")
	public String nuevoCultivo(Model modelo) {
		modelo.addAttribute("origenTratamiento", 0);
		return "cultivo-nuevo";
	}

	@RequestMapping("/cultivo/nuevo/guardado")
	public String nuevoCultivo(Model modelo, Cultivo cultivoNuevo, @RequestParam int origenTratamiento) {
		cultivoRepositorio.save(cultivoNuevo);
		if (origenTratamiento == 0) {
			modelo.addAttribute("cultivos", cultivoRepositorio.findAll());
			return "redirect:/cultivos";
		} else {
			modelo.addAttribute("cultivos", cultivoRepositorio.findAll());
			modelo.addAttribute("productos", productoRepositorio.findAll());
			return "redirect:/tratamiento/nuevo";
		}
	}
}
