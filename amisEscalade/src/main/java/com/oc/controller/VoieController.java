package com.oc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.LongueurRepository;
import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Voie;
import com.oc.forms.VoieForm;
import com.oc.metier.SecteurService;
import com.oc.metier.VoieService;

@Controller
public class VoieController {

	@Autowired
	private VoieService voieService;
	
	@Autowired
	private VoieRepository voieRepository;
	
	@Autowired
	private SecteurService secteurService;

	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	@Autowired 
	private LongueurRepository longueurRepository;
	
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}")
	public String voieSecteur(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie)
	{
	
	SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
	model.addAttribute("site", site);
		
	Secteur secteur= secteurRepository.findById(idSecteur).get();
	model.addAttribute("secteur", secteur);
	
	Voie voie = voieRepository.findById(idVoie).get();
	model.addAttribute("voie", voie);
	
	List<Longueur> longueur = longueurRepository.findByVoie(idVoie);
	model.addAttribute("longueur", longueur);
	
	
	return "voie";
	}
	
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/create")
	public String formVoie(Model model, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
			
		return "formVoie";
	}


	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/create")
	public String ajouterVoie(Model model, @ModelAttribute("voieForm") VoieForm voieForm, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formVoie";
		}
	
		voieService.saveVoie(idSecteur,voieForm, result);

		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;

	}
	
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/edit")
	public String editVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {
		
		Voie voie = voieRepository.findById(idVoie).get();
		model.addAttribute("voie", voie);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		return"editFormVoie";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/edit")
	public String updateVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model, 
			@ModelAttribute("editFormVoie") VoieForm voieForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		voieService.modifyVoie(idVoie, voieForm, result);
		
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
		
	}
			
	
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/delete")
	public String deleteVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model,
			final RedirectAttributes redirectAttributes) {

		voieRepository.deleteById(idVoie);

		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}


}