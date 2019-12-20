

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

import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Voie;
import com.oc.forms.SecteurForm;

@Controller
public class SecteurController {
	
	// injections repositories
	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	@Autowired
	private VoieRepository voieRepository;
	
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}")
	public String secteurSiteEscal(Model model, @PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur) {
		
		SiteEscalade site =siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Secteur secteur =secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		List<Voie> voie = voieRepository.findBySecteur(idSecteur);
		model.addAttribute("voie", voie);
		
		return "secteur";
	}
	
	/*============== #Création ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/create")
	public String formSect(Model model, @PathVariable("idSiteEscalade") long idSiteEscalade) {
		
		SiteEscalade site =siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		return "formSecteur";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/create")
	public String ajouterSecteur(Model model, @ModelAttribute("secteurForm") SecteurForm secteurForm, @PathVariable("idSiteEscalade") long idSiteEscalade,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "formSecteur";
		}
		
		Secteur newSecteur = new Secteur();
		newSecteur.setNomDuSecteur(secteurForm.getNomDuSecteur());
		newSecteur.setLocalisation(secteurForm.getLocalisation());
		newSecteur.setAcces(secteurForm.getAcces());
		SiteEscalade siteSec = siteEscaladeRepository.findById(idSiteEscalade).get();
		newSecteur.setSiteEscalade(siteSec);
		secteurRepository.save(newSecteur);

		return "redirect:/le_site_escalade/"+idSiteEscalade+"/view";
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/edit")
	public String editSecteur(@PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {

		Secteur secteur = secteurRepository.findById(idSecteur).get();
		SecteurForm secForm = new SecteurForm();
		secForm.setIdSecteur(secteur.getIdSecteur());
		secForm.setName(secteur.getNomDuSecteur());
		secForm.setLocalisation(secteur.getLocalisation());
		secForm.setAcces(secteur.getAcces());
		model.addAttribute("secForm", secForm);
		
		return "editFormSecteur";
	}
	
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/update")
	public String updateSecteur(@PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model,
			@ModelAttribute("secteur") SecteurForm secteurForm,
			final RedirectAttributes redirectAttributes) {
		
		Secteur sec = secteurRepository.findById(idSecteur).get();
		sec.setNomDuSecteur(secteurForm.getNomDuSecteur());
		sec.setLocalisation(secteurForm.getLocalisation());
		sec.setAcces(secteurForm.getAcces());
		secteurRepository.save(sec);
		
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/delete")
	public String deleteSecteur(@PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model,
			final RedirectAttributes redirectAttributes) {

		secteurRepository.deleteById(idSecteur);

		return "redirect:/le_site_escalade/"+idSiteEscalade+"/view";
	}

}
