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


import com.oc.forms.VoieForm;
import com.oc.metier.VoieService;

@Controller
@RequestMapping("/formVoie")
public class VoieController {

	@GetMapping()
	public String formSect() {
		return "formVoie";
	}

	@Autowired
	private VoieService voieService;

	@PostMapping()
	public String ajouterVoie(Model model, @ModelAttribute("voieForm") VoieForm voieForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		voieService.saveVoieForm(voieForm, result);

		return "redirect:/formVoie";

	}

}