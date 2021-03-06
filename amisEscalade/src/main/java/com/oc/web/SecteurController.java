

package com.oc.web;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.UserGrimp;
import com.oc.entities.Voie;
import com.oc.forms.SecteurForm;

/**
 * The Class SecteurController.
 */
@Controller
public class SecteurController {
	
	final static Logger logger = LogManager.getLogger(Level.ALL);
	
	// injections repositories
	/** The secteur repository. */
	@Autowired
	private SecteurRepository secteurRepository;
	
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	/** The voie repository. */
	@Autowired
	private VoieRepository voieRepository;
		
	// get and post Mapping
	/*============== #Pages ======================*/
	/**
	 * Secteur site escal. Is a methode for display the site's sector and sector's climbing route 
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @return the sector
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}")
	public String secteurSiteEscal(Model model, @PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur) {
		
		SiteEscalade site =siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Secteur secteur =secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		Iterable<Voie> voie = voieRepository.findBySecteur(idSecteur);
		model.addAttribute("voie", voie);
		
		logger.info("Un utilisateur consulte la liste des secteurs liés au site d'escalade : "+site.getNomSiteEscalade());
		
		return "secteur";
	}
	
	/*============== #Création ======================*/
	/**
	 * Form sect. display a form for add a sector
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @return the formSecteur
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/create")
	public String formSect(Model model, @PathVariable("idSiteEscalade") long idSiteEscalade) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site =siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		SecteurForm sec = new SecteurForm();
		model.addAttribute("secteurForm", sec);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un formulaire de création de secteur pour le site d'escalade : "+site.getNomSiteEscalade());
		
		return "formSecteur";
	}
	
	/**
	 * Ajouter secteur. save a new sector
	 *
	 * @param model the model
	 * @param secteurForm the secteur form
	 * @param idSiteEscalade the id site escalade
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the le_site_escalade
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/create")
	public String ajouterSecteur(Model model, @ModelAttribute("secteurForm") SecteurForm secteurForm, @PathVariable("idSiteEscalade") long idSiteEscalade,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site =siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		if (result.hasErrors()) {
			model.addAttribute("secteurForm", secteurForm);
			return "formSecteur";
		}
		else if (secteurForm.getName().length()>25 || secteurForm.getName().isBlank()) {
			result.rejectValue("name", "name.value", "le nom ne doit pas être vide et dépasser 25 caractères !");
			model.addAttribute("secteurForm", secteurForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de plus de 25 caractères");
			return "formSecteur";
		}
		else if (secteurForm.getLocalisation().isBlank()) {
			result.rejectValue("localisation", "localisation.value", "la description de la localisation ne doit pas être vide !");
			model.addAttribute("secteurForm", secteurForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi une localisation vide");
			return "formSecteur";
		}else if (secteurForm.getLocalisation().length()>255) {
			result.rejectValue("localisation", "localisation.value", "la description de la localisation ne doit dépasser 150 caractères !");
			model.addAttribute("secteurForm", secteurForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi une localisation de plus de 255 caractères");
			return "formSecteur";
		}
		else if (secteurForm.getAcces().isBlank()) {
			result.rejectValue("acces", "acces.value", "la description de l'accès ne doit pas être vide !");
			model.addAttribute("secteurForm", secteurForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi une description d'accès vide !");
			return "formSecteur";
		}else if (secteurForm.getAcces().length()>150) {
			result.rejectValue("acces", "acces.value", "la description de l'accès ne doit pas dépasser 255 caractères !");
			model.addAttribute("secteurForm", secteurForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi une description de plus de 255 caractères");
			return "formSecteur";
		}
		else {
		Secteur newSecteur = new Secteur();
		newSecteur.setNomDuSecteur(secteurForm.getNomDuSecteur());
		newSecteur.setLocalisation(secteurForm.getLocalisation());
		newSecteur.setAcces(secteurForm.getAcces());
		SiteEscalade siteSec = siteEscaladeRepository.findById(idSiteEscalade).get();
		newSecteur.setSiteEscalade(siteSec);
		secteurRepository.save(newSecteur);
		logger.info("l'utilisateur "+usr.getPseudo()+" a créer un nouveau secteur "+newSecteur.getNomDuSecteur()+" pour le site d'escalade "+site.getNomSiteEscalade());
		}
		return "redirect:/le_site_escalade/"+idSiteEscalade+"/view";
	}
	
	/*============== #Modification ======================*/
	/**
	 * Edits the secteur.
	 *
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @return the editFormSecteur
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/edit")
	public String editSecteur(@PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {

		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		SecteurForm secForm = new SecteurForm();
		secForm.setIdSecteur(secteur.getIdSecteur());
		secForm.setName(secteur.getNomDuSecteur());
		secForm.setLocalisation(secteur.getLocalisation());
		secForm.setAcces(secteur.getAcces());
		model.addAttribute("secForm", secForm);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un forulaire de modification de secteur pour le secteur n°"+idSecteur);
		
		return "editFormSecteur";
	}
	
	/**
	 * Update secteur. save the sector's modifications
	 *
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param secteurForm the secteur form
	 * @param model the model
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the sector page
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/update")
	public String updateSecteur(@PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, @ModelAttribute("secForm") SecteurForm secteurForm, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		if (result.hasErrors()) {
			model.addAttribute("secForm", secteurForm);
			return "editFormSecteur";
		}
		else if (secteurForm.getName().isBlank()) {
			result.rejectValue("name", "name.value", "le nom ne doit pas être vide !");
			model.addAttribute("secForm", secteurForm);
			logger.error("l'utilisateur "+usr.getUsername()+" a saisi un nom vide !");
			return "editFormSecteur";
		}
		else if (secteurForm.getName().length()>25) {
			result.rejectValue("name", "name.value", "le nom ne doit pas dépasser 25 caractères !");
			model.addAttribute("secForm", secteurForm);
			logger.error("l'utilisateur "+usr.getUsername()+" a saisi un nom de plus de 25 caractères !");
			return "editFormSecteur";
		}		
		else if (secteurForm.getAcces().isBlank()) {
			result.rejectValue("acces", "acces.value", "la description de l'accès ne doit pas être vide !");
			model.addAttribute("secForm", secteurForm);
			logger.error("l'utilisateur "+usr.getUsername()+" a saisi une description d'accès vide !");
			return "editFormSecteur";
		}
		else if (secteurForm.getAcces().length()>255) {
			result.rejectValue("acces", "acces.value", "la description de l'accès ne doit pas dépasser 255 caractères !");
			model.addAttribute("secForm", secteurForm);
			logger.error("l'utilisateur "+usr.getUsername()+" a saisi une description d'accès de plus de 255 caractères !");
			return "editFormSecteur";
		}
		else if (secteurForm.getLocalisation().isBlank()) {
			result.rejectValue("localisation", "localisation.value", "la description de la localisation ne doit pas être vide !");
			model.addAttribute("secForm", secteurForm);
			logger.error("l'utilisateur "+usr.getUsername()+" a saisi une description de localisation vide !");
			return "editFormSecteur";
		}		
		else if (secteurForm.getLocalisation().length()>150) {
			result.rejectValue("localisation", "localisation.value", "la description de la localisation ne doit pas dépasser 150 caractères !");
			model.addAttribute("secForm", secteurForm);
			logger.error("l'utilisateur "+usr.getUsername()+" a saisi une description de localisation de plus de 150 caractères !");
			return "editFormSecteur";
		}
		else {
		Secteur sec = secteurRepository.findById(idSecteur).get();
		sec.setNomDuSecteur(secteurForm.getNomDuSecteur());
		sec.setLocalisation(secteurForm.getLocalisation());
		sec.setAcces(secteurForm.getAcces());
		secteurRepository.save(sec);
		logger.info("l'utilisateur "+usr.getPseudo()+" a enregistré les modifications apportées au secteur n°"+idSecteur);
		}
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}
	
	/*============== #Suppression ======================*/
	/**
	 * Delete secteur.delete sectors
	 *
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the le_site_escalade page
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/delete")
	public String deleteSecteur(@PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model,
			final RedirectAttributes redirectAttributes) {

		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		logger.warn("l'utilisateur "+usr.getPseudo()+" a supprimer le secteur n°"+idSecteur);
		secteurRepository.deleteById(idSecteur);

		return "redirect:/le_site_escalade/"+idSiteEscalade+"/view";
	}

}
