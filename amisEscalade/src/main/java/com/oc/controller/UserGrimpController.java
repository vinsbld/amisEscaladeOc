package com.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.oc.forms.UserGrimpForm;
import com.oc.metier.UserGrimpService;

@Controller
public class UserGrimpController {

	@Autowired
	private UserGrimpService userGrimpService;

	@PostMapping("/inscription")
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimpForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		userGrimpService.saveUserGrimpForm(userGrimpForm, result);

			return "redirect:/inscription";
		
	}

}
