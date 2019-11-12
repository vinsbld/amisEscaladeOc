package com.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formLogIn")
public class ConnexionControlleur {
	
	@GetMapping
	public String formConnect() {
		return "formLogIn";
	}

}
