package com.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmisController {
	
	@GetMapping("/inscription")
	public String inscription() {
		return "formInscription";
		
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
