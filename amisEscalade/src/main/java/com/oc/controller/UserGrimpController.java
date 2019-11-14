package com.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.entities.UserGrimp;
import com.oc.forms.UserGrimpForm;
import com.oc.metier.UserGrimpService;

@Controller
public class UserGrimpController {
	
	@Autowired
	private UserGrimpService userGrimpService;
	
	@GetMapping("/inscription")
	public String formInsc() {
		return "formInscription";
	}
	

	@PostMapping("/inscription")
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimp,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		userGrimpService.saveUserGrimpForm(userGrimp, result);

			return "redirect:/index";
			
			
			
		
	}

}
