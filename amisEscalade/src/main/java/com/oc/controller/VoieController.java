package com.oc.controller;

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
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Secteur;
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
	
	@GetMapping("/secteur/{idSecteur}/voie")
	public String voieSecteur(Model model, @PathVariable("idSecteur") long idSecteur,
			
			@RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "4") int s,
			@RequestParam(name = "motCle", defaultValue = "") String motCle)
	{
	
	Secteur secteurs= secteurRepository.findById(idSecteur).get();
	Page<Voie> pageVoies = voieRepository.chercher("%" + motCle +"%", new PageRequest(p, s));
	
	model.addAttribute("addVoieToSecteur", secteurs);
	model.addAttribute("listVoie", pageVoies.getContent());
	
	int[] pages = new int[pageVoies.getTotalPages()];
	model.addAttribute("pages", pages);
	model.addAttribute("size", s);
	model.addAttribute("pageCourante", p);
	model.addAttribute("motCle", motCle);
	
	return "voie";
	}
	
	@GetMapping("/secteur/{idSecteur}/voie/create")
	public String formVoie(Model model, @PathVariable("idSecteur") long idSecteur) {
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		
		return "formVoie";
	}


	@PostMapping("/secteur/{idSecteur}/voie/create")
	public String ajouterVoie(Model model, @ModelAttribute("voieForm") VoieForm voieForm, @PathVariable("idSecteur") long idSecteur, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formVoie";
		}
	
		voieService.saveVoie(idSecteur,voieForm, result);

		return "redirect:/secteur/"+idSecteur+"/voie";

	}
	
	@GetMapping("/secteur/{idSecteur}/voie/{idVoie}/edit")
	public String editVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, Model model) {
		
		Voie v = voieRepository.findById(idVoie).get();
		
		model.addAttribute("editVoie", idVoie);
		
		return"editFormVoie";
	}
	
	@PostMapping("/secteur/{idSecteur}/voie/{idVoie}/update")
	public String updateVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, Model model, 
			@ModelAttribute("editFormVoie") VoieForm voieForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		voieService.modifyVoie(idVoie, voieForm, result);
		
		return "redirect:/secteur"+idSecteur+"/voie";
		
	}
			
	
	@GetMapping("/secteur/{idSecteur}/voie/{idVoie}/delete")
	public String deleteVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, Model model,
			final RedirectAttributes redirectAttributes) {

		voieRepository.deleteById(idVoie);

		return "redirect:/secteur/"+idSecteur+"/voie";
	}


}