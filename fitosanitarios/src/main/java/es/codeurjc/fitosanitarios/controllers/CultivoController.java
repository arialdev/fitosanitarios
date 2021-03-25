package es.codeurjc.fitosanitarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.codeurjc.fitosanitarios.repositories.TratamientoRepository;

@Controller
public class CultivoController {

	@Autowired
	private static TratamientoRepository tratamientoRepository;
}
