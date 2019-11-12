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


import com.oc.forms.UserGrimpForm;
import com.oc.metier.UserGrimpService;

@Controller
@RequestMapping("/inscription")
public class UserGrimpController {
	
	@GetMapping
	public String formInsc() {
		return "formInscription";
	}
	
	@Autowired
	private UserGrimpService userGrimpService;

	@PostMapping
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimpForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		userGrimpService.saveUserGrimpForm(userGrimpForm, result);

			return "formInscription";
		
	}

}
