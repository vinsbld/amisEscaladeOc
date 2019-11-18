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

import com.oc.forms.LongueurForm;
import com.oc.metier.LongueurService;


@Controller
@RequestMapping("/longueur")
public class LongueurController {
	
	@Autowired
	private LongueurService longueurService;
	
	@GetMapping
	public String formLong() {
		return "formLongueur";
	}
	
	@PostMapping
	public String ajouterLongueur(Model model, @ModelAttribute("longueurForm") LongueurForm longueurForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		longueurService.saveLongueurForm(longueurForm, result);
		
		return"redirect:/formLongueur";
		
	}

}
