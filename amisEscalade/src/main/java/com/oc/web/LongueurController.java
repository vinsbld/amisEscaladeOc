package com.oc.web;

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
import com.oc.forms.LongueurForm;


@Controller
public class LongueurController {
	
	// injections repositories
	@Autowired
	private LongueurRepository longueurRepository;
	@Autowired
	private VoieRepository voieRepository;
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	@Autowired
	private SecteurRepository secteurRepository;
	
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur")
	public String longueurVoie(Model model, @PathVariable("idVoie") long idVoie, @PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur){
		
		Voie voie = voieRepository.findById(idVoie).get();		
		model.addAttribute("voie", voie);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		return "voie";
	}
	/*============== #Création ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/create")
	public String formLong(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade,@PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie) {
		
		Voie voie = voieRepository.findById(idVoie).get();
		model.addAttribute("voie", voie);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		return "formLongueur";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/create")
	public String ajouterLongueur(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade,@PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @ModelAttribute("longueurForm") LongueurForm longueurForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formLongueur";
		}else {
			Longueur newLongueur = new Longueur();
			newLongueur.setDistance(longueurForm.getDistance());
			newLongueur.setHauteur(longueurForm.getHauteur());
			Voie voie = voieRepository.findById(idVoie).get();
			newLongueur.setVoie(voie);
			longueurRepository.save(newLongueur);
		}
		return"redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+ idVoie;	
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/{idLongueur}/edit")
	public String editSecteur(@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @PathVariable("idLongueur") long idLongueur, Model model) {
		
			Longueur longueur = longueurRepository.findById(idLongueur).get();
			LongueurForm longForm = new LongueurForm();
			longForm.setDistance(longueur.getDistance());
			longForm.setHauteur(longueur.getHauteur());
			model.addAttribute("longueur", longForm);
			
		return"editFormLongueur";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/{idLongueur}/update")
	public String editLongueur(@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @PathVariable("idLongueur") long idLongueur, Model model, @ModelAttribute("editFormLongueur") LongueurForm longueurForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "editFormLongueur";
		}else {
		Longueur longr = longueurRepository.findById(idLongueur).get();
		longr.setDistance(longueurForm.getDistance());
		longr.setHauteur(longueurForm.getHauteur());
		longueurRepository.save(longr);
		}
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+ idVoie;
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/{idLongueur}/delete")
	public String deleteLongueur(@PathVariable("idLongueur") long idLongueur, @PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model, final RedirectAttributes redirectAttributes) {
		
		longueurRepository.deleteById(idLongueur);
		
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+ idVoie;
	}

}
