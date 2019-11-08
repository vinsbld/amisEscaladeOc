package com.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.forms.SecteurForm;
import com.oc.metier.SecteurService;

@Controller
public class SecteurController {
	
	@Autowired
	private SecteurService secteurService;
	
	@PostMapping("/formSecteur")
	public String ajouterSecteur(Model model, @ModelAttribute("secteurForm") SecteurForm secteurForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		secteurService.saveSecteurForm(secteurForm, result);
		
		return"redirect:/formSecteur";
	}

}
