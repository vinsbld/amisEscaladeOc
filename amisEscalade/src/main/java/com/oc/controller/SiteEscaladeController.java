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

import com.oc.forms.SiteEscaladeForm;
import com.oc.metier.SiteEscaladeService;

@Controller
@RequestMapping("/formSiteEscalade")
public class SiteEscaladeController {
	
	@GetMapping
	public String formSit() {
		return"formSiteEscalade";
	}
	
	@Autowired
	private SiteEscaladeService siteEscaladeService;

	@PostMapping
	public String ajouterSiteEscalade(Model model, @ModelAttribute("siteEscaladeForm") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		siteEscaladeService.saveSiteEscalade(siteEscaladeForm, result);
		
		return "formSiteEscalade";
	}
}
