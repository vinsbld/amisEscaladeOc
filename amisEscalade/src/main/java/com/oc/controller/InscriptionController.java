package com.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InscriptionController {
	
	@GetMapping("/inscription")
	public String inscription() {
		return "formInscription";
		
	}
	

}
