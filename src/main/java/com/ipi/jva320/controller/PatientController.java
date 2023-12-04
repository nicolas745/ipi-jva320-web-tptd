package com.ipi.jva320.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PatientController {
	@GetMapping(value = "/patients/{id}")
		public String getPatient(@PathVariable int id,final ModelMap model) {
		String sexe = "M";
		model.put("id", id);
		model.put("nom", "Jean");
		model.put("ex", sexe == "M" ? "John" : "Anna");
		return "patients";
	}
}