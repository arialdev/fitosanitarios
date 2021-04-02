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

	@RequestMapping("/cultivos/view/{id}")
	public String viewCultivoView(@PathVariable Long id, Model model) {
		Optional<Cultivo> cultivo = cultivoRepository.findById(id);
		if (cultivo.isPresent()) {
			model.addAttribute("cultivo", cultivo.get());
			return "cultivo-detail-view";
		}
		return "cultivos";
	}

	@RequestMapping("/cultivos/update/{id}")
	public String modifyCultivoView(@PathVariable Long id, Model model) {
		Optional<Cultivo> cultivo = cultivoRepository.findById(id);
		if (cultivo.isPresent()) {
			model.addAttribute("cultivo", cultivo.get());
			return "cultivo-detail-modify";
		}
		return "cultivos";
	}
}
