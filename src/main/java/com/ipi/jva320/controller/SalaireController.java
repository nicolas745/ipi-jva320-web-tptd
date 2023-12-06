package com.ipi.jva320.controller;

import java.time.LocalDate;

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
		SalarieAideADomicile salarier = salarieAideADomicileService.getSalaries().get(id);
		model.put("nbsalaire", nbsalaire);
		model.put("id", id);
		model.put("nom",salarier.getNom());
		model.put("moisCour", salarier.getMoisEnCours());
		model.put("moisDebutContrat", salarier.getMoisDebutContrat());
		model.put("joursTravaillesAnneeN", salarier.getJoursTravaillesAnneeN());
		model.put("congesPayesAcquisAnneeN", salarier.getCongesPayesAcquisAnneeN());
		model.put("joursTravaillesAnneeNMoins1", salarier.getJoursTravaillesAnneeNMoins1());
		model.put("congesPayesAcquisAnneeNMoins1", salarier.getCongesPayesAcquisAnneeNMoins1());
		model.put("congesPayesPrisAnneeNMoins1", salarier.getCongesPayesPrisAnneeNMoins1());
		return "detail_Salarie";
	}
}