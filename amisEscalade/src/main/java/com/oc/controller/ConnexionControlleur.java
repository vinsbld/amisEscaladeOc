package com.oc.controller;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ConnexionControlleur {
	
	@GetMapping("/connexion")
	public ModelAndView loginGet (Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("formLogIn");
	}
	


}
