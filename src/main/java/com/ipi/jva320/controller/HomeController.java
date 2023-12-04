package com.ipi.jva320.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public String home(final ModelMap model) {
		model.put("message", "Hello World !");
		return "index";
	}

}
