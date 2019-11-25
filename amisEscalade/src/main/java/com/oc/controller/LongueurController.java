package com.oc.controller;

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

import com.oc.dao.LongueurRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Voie;
import com.oc.forms.LongueurForm;
import com.oc.metier.LongueurService;


@Controller
public class LongueurController {
	
	@Autowired
	private LongueurService longueurService;
	
	@Autowired
	private LongueurRepository longueurRepository;
	
	@Autowired
	private VoieRepository voieRepository;
	
	@GetMapping("/voie/{idVoie}/longueur/create")
	public String formLong(Model model, @PathVariable("idLongueur") long idLongueur) {
		
		Voie voies = voieRepository.findById(idLongueur).get();
		model.addAttribute("addLongr", voies);
		return "formLongueur";
	}
	
	@PostMapping("/voie/{idVoie}/longueur/create")
	public String ajouterLongueur(Model model, @ModelAttribute("longueurForm") LongueurForm longueurForm,
			@PathVariable("idVoie") long idVoie, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formLongueur";
		}
		
		longueurService.saveLongueur(idVoie, longueurForm, result);
		
		return"redirect:/voie"+ idVoie +"/longueur";
		
	}
	
	@GetMapping("/voie/{idVoie}/longueur")
	public String longueurVoie(Model model, @PathVariable("idVoie") long idVoie, 
			
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "4") int s,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		
		Voie voies = voieRepository.findById(idVoie).get();
		
		Page<Longueur> pageLongueurs = longueurRepository.chercher("%"+ motCle +"%", new PageRequest(p, s));
		
		model.addAttribute("addLongrToVoie", voies);
		model.addAttribute("listeLongueur", pageLongueurs.getContent());
		
		int[] pages = new int[pageLongueurs.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		
		return "longueur";
	}
	

}
