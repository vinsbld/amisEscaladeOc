package com.oc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.CodexRepository;
import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.Codex;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.UserGrimp;
import com.oc.forms.SiteEscaladeForm;

@Controller
public class SiteEscaladeController {
	
	//injections repositories
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	@Autowired
	private SecteurRepository secteurRepository;
	@Autowired
	private CodexRepository codexRepository;
	
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site_escalade")
	public String siteEscal(Model model) {
		
		Iterable<SiteEscalade> listeSite = siteEscaladeRepository.findAll();
		model.addAttribute("listSite", listeSite);

		return "site_escalade";
	}
	
	@GetMapping("/le_site_escalade/{idSiteEscalade}/view")
	public String leSite(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Secteur> sec = secteurRepository.findBySite(idSiteEscalade);
		model.addAttribute("sec", sec);
		
		return"le_site_escalade";
	}
	
	/*============== #Création ======================*/
	@GetMapping("/site_escalade/create")
	public String formSit(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);

		Iterable<Codex> cdxList = codexRepository.findAll();
		model.addAttribute("cdxList", cdxList);
	
		return"formSiteEscalade";
	}

	@PostMapping("/site_escalade/create")
	public String ajouterSiteEscalade(Model model, @ModelAttribute("siteEscaladeForm") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "formSiteEscalade";
		}else {
			SiteEscalade newSiteEscalade = new SiteEscalade();
			newSiteEscalade.setIdSiteEscalade(siteEscaladeForm.getIdSiteEscalade());
			newSiteEscalade.setNomSiteEscalade(siteEscaladeForm.getSiteName()); 
			newSiteEscalade.setDepartement(siteEscaladeForm.getDepartement()); 
			newSiteEscalade.setVille(siteEscaladeForm.getVille());
			newSiteEscalade.setCodePostal(siteEscaladeForm.getCodePostal());
			newSiteEscalade.setOfficiel(siteEscaladeForm.isOfficiel());
			UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			newSiteEscalade.setUserGrimp(usr);
			siteEscaladeRepository.save(newSiteEscalade);
		}

		return "redirect:/site_escalade";
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/edit")
	public String editSite(@ModelAttribute("siteEscaladeForm") SiteEscaladeForm siteEscaladeForm, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {
		
		Iterable<Codex> cdxList = codexRepository.findAll();
		model.addAttribute("cdxList", cdxList);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		SiteEscaladeForm siteForm = new SiteEscaladeForm();
		siteForm.setIdSiteEscalade(site.getIdSiteEscalade());
		siteForm.setSiteName(site.getNomSiteEscalade());
		siteForm.setDepartement(site.getDepartement());
		siteForm.setVille(site.getVille());
		siteForm.setCodePostal(site.getCodePostal());
		siteForm.setOfficiel(site.isOfficiel());
		model.addAttribute("siteForm", siteForm);
		 
		return "editFormSiteEscalade";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/update")
	public String updateSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, @ModelAttribute("siteEscalade") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "editFormSiteEscalade";
		}else {
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		site.setNomSiteEscalade(siteEscaladeForm.getSiteName());
		site.setDepartement(siteEscaladeForm.getDepartement());
		site.setVille(siteEscaladeForm.getVille());
		site.setCodePostal(siteEscaladeForm.getCodePostal());
		site.setOfficiel(siteEscaladeForm.isOfficiel());
		siteEscaladeRepository.save(site);
		}
		return "redirect:/le_site_escalade/"+idSiteEscalade+"/view";
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/delete")
	public String deleteSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, final RedirectAttributes redirectAttributes) {
		
		siteEscaladeRepository.deleteById(idSiteEscalade);
		
		return "redirect:/site_escalade";
				
	}
	
}
