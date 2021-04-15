package es.codeurjc.fitosanitarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.codeurjc.fitosanitarios.models.Cultivo;
import es.codeurjc.fitosanitarios.repositories.CultivoRepository;

@Controller
public class CultivoController {

	@Autowired
	private CultivoRepository cultivoRepository;

	@RequestMapping("/cultivos")
	public String board(Model model) {
		List<Cultivo> cultivos = cultivoRepository.findAll();
		model.addAttribute("cultivos", cultivos);
		return "cultivos";
	}

	@RequestMapping("/cultivo/view/{id}")
	public String viewCultivo(@PathVariable Long id, Model model) {
		Optional<Cultivo> cultivo = cultivoRepository.findById(id);
		if (cultivo.isPresent()) {
			model.addAttribute("cultivo", cultivo.get());
			return "cultivo-detail-view";
		}
		return "error";
	}

	@RequestMapping("/cultivo/update/{id}")
	public String modifyCultivo(@PathVariable Long id, Model model) {
		Optional<Cultivo> cultivo = cultivoRepository.findById(id);
		if (cultivo.isPresent()) {
			model.addAttribute("cultivo", cultivo.get());
			return "cultivo-detail-modify";
		}
		return "error";
	}

	@RequestMapping("/cultivo/update/save/{id}")
	public String updateCultivo(@PathVariable Long id, Model model, Cultivo updatedCultivo) {
		Optional<Cultivo> cultivo = cultivoRepository.findById(id);
		if (cultivo.isPresent()) {
			cultivoRepository.save(cultivo.get().update(updatedCultivo));
			return "cultivo/view/" + id;
		}
		return "error";
	}

	@RequestMapping("/cultivo/delete/{id}")
	public String deleteCultivo(@PathVariable Long id, Model model) {
		Optional<Cultivo> cultivo = cultivoRepository.findById(id);
		if (cultivo.isPresent()) {
			cultivoRepository.delete(cultivo.get());
			return "cultivos";
		}
		return "error";
	}

	@RequestMapping("/cultivo/new")
	public String newCultivo(Model model) {
		return "cultivo-new";
	}

	@RequestMapping("/cultivo/new/save")
	public String newCultivo(Model model, Cultivo newCultivo) {
		cultivoRepository.save(newCultivo);
		return "cultivos";
	}
}
