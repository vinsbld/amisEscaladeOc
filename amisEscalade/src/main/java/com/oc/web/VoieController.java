package com.oc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

@Controller
public class VoieController {

	// injections repositories
	@Autowired
	private VoieRepository voieRepository;

	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	@Autowired 
	private LongueurRepository longueurRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}")
	public String voieSecteur(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie){
	
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
	
	/*============== #Création ======================*/
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
		Voie newVoie = new Voie();
		newVoie.setNomDeVoie(voieForm.getName());
		newVoie.setCotation(voieForm.getCotation());
		Secteur voieSec = secteurRepository.findById(idSecteur).get();
		newVoie.setSecteur(voieSec);
		voieRepository.save(newVoie);
		
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/edit")
	public String editVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {

		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
	  
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		Voie voie = voieRepository.findById(idVoie).get();
		VoieForm voiForm = new VoieForm();
		voiForm.setIdVoie(voie.getIdVoie());
		voiForm.setName(voie.getNomDeVoie());
		voiForm.setCotation(voie.getCotation());
		model.addAttribute("voiForm", voiForm);
		
		return"editFormVoie";
	}
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/update")
	public String updateVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model, 
			@ModelAttribute("voie") VoieForm voieForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		Voie voie = voieRepository.findById(idVoie).get();
		voie.setNomDeVoie(voieForm.getName());
		voie.setCotation(voieForm.getCotation());
		voieRepository.save(voie);
		
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+idVoie;	
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/delete")
	public String deleteVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model,
			final RedirectAttributes redirectAttributes) {

		voieRepository.deleteById(idVoie);

		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}

}