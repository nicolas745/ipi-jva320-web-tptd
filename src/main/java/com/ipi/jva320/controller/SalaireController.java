package com.ipi.jva320.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
@Controller
public class SalaireController {
	@Autowired
	private SalarieAideADomicileService salarieAideADomicileService;
	@GetMapping(value = "/salaries/{id}")
	public String home(@PathVariable int id,final ModelMap model) {
		long nbsalaire = salarieAideADomicileService.countSalaries();
		SalarieAideADomicile salarier = salarieAideADomicileService.getSalaries().get(id-1);
		model.put("nbsalaire", nbsalaire);
		model.put("id", id);
		System.out.println(salarier.getNom());
		model.put("nom",salarier.getNom());
		model.put("prenom", "test");
		return "detail_Salarie";
	}
}