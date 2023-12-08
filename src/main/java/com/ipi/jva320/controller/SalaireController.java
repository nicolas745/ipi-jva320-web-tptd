package com.ipi.jva320.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
@Controller
public class SalaireController {
	@Autowired
	private SalarieAideADomicileService salarieAideADomicileService;
	@GetMapping(value = "/salaries/{id}")
	public String home(@PathVariable int id,final ModelMap model) {
		SalarieAideADomicile salarier = salarieAideADomicileService.getSalaries().get(id-1);
		model.addAttribute("salarie", salarier);
		return "detail_Salarie";
	}
	
	@GetMapping(value ="/salaries/aide/new")
	public String salariesnew(final ModelMap model) {
		SalarieAideADomicile salarier = new SalarieAideADomicile();
		model.addAttribute("salarie", salarier);
		return "detail_Salarie";
	}
	@PostMapping(value = "/salaries/aide/new")
	public String postsalariesnew(@RequestParam Map<String, String> post, final ModelMap model) throws SalarieException {
		SalarieAideADomicile salarier = new SalarieAideADomicile(post.get("nom"), LocalDate.parse(post.get("moisDebutContrat")), LocalDate.parse(post.get("moisEnCours")),
               Double.parseDouble(post.get("joursTravaillesAnneeN")), Double.parseDouble(post.get("congesPayesAcquisAnneeN")),
               Double.parseDouble(post.get("joursTravaillesAnneeNMoins1")), Double.parseDouble(post.get("congesPayesAcquisAnneeNMoins1")),  Double.parseDouble(post.get("congesPayesPrisAnneeNMoins1")));
		System.out.println(post);
		salarieAideADomicileService.creerSalarieAideADomicile(salarier);
		model.addAttribute("salarie", salarier);
		return "detail_Salarie";
	}
	@GetMapping(value = "/salaries/{id}/delete")
	public String delete(@PathVariable Long id, ModelMap model) throws EntityExistsException, SalarieException {
		salarieAideADomicileService.deleteSalarieAideADomicile(id);
		return "redirect:/salaries?page=0&size=10&sortProperty=nom&sortDirection=ASC"; 
	}
	@GetMapping(value = "/salaries")
	public String salaries(@RequestParam("page") int page, @RequestParam("size") Long size, @RequestParam("sortProperty") String nom,@RequestParam("sortDirection") String sortDirection, final ModelMap model) {
		List<SalarieAideADomicile> listsalaries = salarieAideADomicileService.getSalaries();
		model.addAttribute("salaries",listsalaries); 
		model.put("next", page+1);
		model.put("last", page-1);
		return "list"; 
	}
}