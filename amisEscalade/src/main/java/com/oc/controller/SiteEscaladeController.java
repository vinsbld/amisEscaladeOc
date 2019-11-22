package com.oc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.SiteEscalade;
import com.oc.forms.SiteEscaladeForm;
import com.oc.metier.SiteEscaladeService;

@Controller
public class SiteEscaladeController {
	
	@Autowired
	private SiteEscaladeService siteEscaladeService;
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;

	@GetMapping("/site_escalade/create")
	public String formSit() {
		return"formSiteEscalade";
	}

	@PostMapping("/site_escalade/create")
	public String ajouterSiteEscalade(Model model, @ModelAttribute("siteEscaladeForm") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "formSiteEscalade";
		}
		
		siteEscaladeService.saveSiteEscalade(siteEscaladeForm, result);
		
		return "site_escalade";
	}
	
	@GetMapping("/site_escalade")
	public String siteEscal(Model model,
			@RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue="4")int s,
			@RequestParam(name="motCle", defaultValue="")String motCle) {
		Page<SiteEscalade> pageSites = siteEscaladeRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		
		model.addAttribute("listSite", pageSites.getContent());
		int[]pages = new int[pageSites.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "site_escalade";
	}
	
	@GetMapping("/site_escalade/{idSiteEscalade}/edit")
	public String editSite(@PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {
		Optional<SiteEscalade> s =siteEscaladeRepository.findById(idSiteEscalade);
		SiteEscalade siteEscalade = null;
		if(s.isPresent()) {
			siteEscalade = s.get();
		}
		model.addAttribute("siteEscalade", siteEscalade);
		return "editFormSiteEscalade";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/update")
	public String updateSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, @ModelAttribute("editSiteEscaladeForm") SiteEscaladeForm editSiteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		siteEscaladeService.saveSiteEscalade(editSiteEscaladeForm, result);
			
		return "redirect:/site_escalade";
	}
	

	
	@GetMapping("/site_escalade/{idSiteEscalade}/delete")
	public String deleteSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, final RedirectAttributes redirectAttributes) {
		
		siteEscaladeRepository.deleteById(idSiteEscalade);
		return "redirect:/site_escalade";
				
	}
	
	
	
}
