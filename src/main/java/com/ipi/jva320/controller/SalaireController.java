package com.ipi.jva320.controller;
import java.time.LocalDate;
import java.util.ArrayList;
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
	public String home(@PathVariable long id,final ModelMap model) {
		SalarieAideADomicile salarier = salarieAideADomicileService.getSalarie(id);
		if(salarier==null) {
			if(salarieAideADomicileService.getSalaries().size()==0) {
				return "redirect:/salaries";
			}else {
				return "redirect:/salaries/"+salarieAideADomicileService.getSalaries().get(0).getId().intValue();
			}
		}
		model.addAttribute("salarie", salarier);
		return "detail_Salarie";
	}
	
	@PostMapping(value = "/salaries/{id}")
	public String edit(ModelMap model, @PathVariable Long id, @RequestParam Map<String, String> post) {
		System.out.println(post);
		SalarieAideADomicile salaire = salarieAideADomicileService.getSalarie(id);
		
		salaire.setCongesPayesAcquisAnneeN(Double.parseDouble(post.get("congesPayesAcquisAnneeN")));
		salaire.setCongesPayesAcquisAnneeNMoins1(Double.parseDouble(post.get("congesPayesAcquisAnneeNMoins1")));
		salaire.setCongesPayesPrisAnneeNMoins1(Double.parseDouble(post.get("congesPayesPrisAnneeNMoins1")));
		salaire.setJoursTravaillesAnneeN(Double.parseDouble(post.get("joursTravaillesAnneeN")));
		salaire.setJoursTravaillesAnneeNMoins1(Double.parseDouble(post.get("joursTravaillesAnneeNMoins1")));
		model.addAttribute("salarie", salarieAideADomicileService.getSalarie(id));
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
	public String salaries(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Long size,
            @RequestParam(value = "sortProperty", required = false) String nom,
            @RequestParam(value = "sortDirection", required = false) String sortDirection,
            @RequestParam(value="nom", required = false) String research,
            final ModelMap model) {
		if(research!=null) {
			List<SalarieAideADomicile> listsalaries = salarieAideADomicileService.getSalaries();
			List<SalarieAideADomicile> select = new ArrayList<SalarieAideADomicile>();
			for (SalarieAideADomicile salarieAideADomicile : listsalaries) {
				System.out.println(salarieAideADomicile.getNom() + " : "+research);
				if(salarieAideADomicile.getNom().equals(research)) {
					System.out.println("dd");
					select.add(salarieAideADomicile);
				}
			}
			model.addAttribute("salaries",select); 
		}else{
			List<SalarieAideADomicile> listsalaries = salarieAideADomicileService.getSalaries();
			model.addAttribute("salaries",listsalaries); 
		}
		if(page==null) {
			page=0;
		}
		model.put("next", page+1);
		model.put("last", page-1);
		return "list"; 
	}
}