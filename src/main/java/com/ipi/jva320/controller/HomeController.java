package com.ipi.jva320.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipi.jva320.service.SalarieAideADomicileService;

@Controller
public class HomeController {
	@Autowired
	private SalarieAideADomicileService salarieAideADomicileService;
	@GetMapping(value = "/")
	public String home(final ModelMap model) {
		long nbsalaire = salarieAideADomicileService.countSalaries();
		model.put("nbsalaire", nbsalaire);
		return "home";
	}

}