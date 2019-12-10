package com.oc.controller;

import java.util.List;
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

import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.forms.SiteEscaladeForm;
import com.oc.metier.SiteEscaladeService;

@Controller
public class SiteEscaladeController {
	
	@Autowired
	private SiteEscaladeService siteEscaladeService;
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	@Autowired
	private SecteurRepository secteurRepository;

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
		
		SiteEscalade newSiteEscalade = new SiteEscalade();
		newSiteEscalade.setIdSiteEscalade(siteEscaladeForm.getIdSiteEscalade());
		newSiteEscalade.setNomSiteEscalade(siteEscaladeForm.getName());
		newSiteEscalade.setDepartement(siteEscaladeForm.getDepartement());
		newSiteEscalade.setVille(siteEscaladeForm.getVille());
		newSiteEscalade.setOfficiel(siteEscaladeForm.getOfficiel());
		
		siteEscaladeService.saveSiteEscalade(newSiteEscalade);
		
		return "redirect:/site_escalade";
	}
	
	@GetMapping("/site_escalade")
	public String siteEscal(Model model) {
		
		List<SiteEscalade> listeSite = siteEscaladeRepository.findAll();
		model.addAttribute("listSite", listeSite);

		return "site_escalade";
	}
	
	@GetMapping("/site_escalade/{idSiteEscalade}/edit")
	public String editSite(@PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {
		
		Optional<SiteEscalade> s =siteEscaladeRepository.findById(idSiteEscalade);
		
		SiteEscalade siteEscalade = null;
		if(s.isPresent()) {
			siteEscalade = s.get();
		}
		
		
		SiteEscaladeForm siteForm = new SiteEscaladeForm();
		siteForm.setName(siteEscalade.getNomSiteEscalade());
		siteForm.setIdSiteEscalade(siteEscalade.getIdSiteEscalade());
		siteForm.setDepartement(siteEscalade.getDepartement());
		siteForm.setVille(siteEscalade.getVille());
		siteForm.setOfficiel(siteEscalade.getOfficiel());
		model.addAttribute("siteForm", siteForm);
		
		return "editFormSiteEscalade";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/update")
	public String updateSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, @ModelAttribute("siteEscalade") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {

			
		siteEscaladeService.modifySiteEscalade(idSiteEscalade, siteEscaladeForm);
			
		return "redirect:/le_site_escalade/{idSiteEscalade}/view";
	}
	

	
	@GetMapping("/site_escalade/{idSiteEscalade}/delete")
	public String deleteSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, final RedirectAttributes redirectAttributes) {
		
		siteEscaladeRepository.deleteById(idSiteEscalade);
		
		return "redirect:/site_escalade";
				
	}
	
	@GetMapping("/le_site_escalade/{idSiteEscalade}/view")
	public String leSite(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		List<Secteur> sec = secteurRepository.findBySite(idSiteEscalade);
		model.addAttribute("sec", sec);
		
		
		return"le_site_escalade";
	}

	
}
