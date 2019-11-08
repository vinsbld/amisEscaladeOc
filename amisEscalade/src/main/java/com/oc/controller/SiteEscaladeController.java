package com.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.forms.SiteEscaladeForm;
import com.oc.metier.SiteEscaladeService;

@Controller
public class SiteEscaladeController {
	
	@GetMapping("/formSite")
	public String formSit() {
		return"formSite";
	}
	
	@Autowired
	private SiteEscaladeService siteEscaladeService;

	@GetMapping("/formSite")
	public String ajouterSiteEscalade(Model model, @ModelAttribute("siteEscaladeForm") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		siteEscaladeService.saveSiteEscalade(siteEscaladeForm, result);
		
		return "redirect:/formSite";
	}
}
