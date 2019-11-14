package com.oc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/connexion")
public class ConnexionControlleur {
	
	@GetMapping
	public String formConnect(Model model) {
		return "formLogIn";
	}
	


}
