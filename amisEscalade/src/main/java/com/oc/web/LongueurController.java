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

import com.oc.dao.LongueurRepository;
import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.UserGrimp;
import com.oc.entities.Voie;
import com.oc.forms.LongueurForm;


/**
 * The Class LongueurController.
 */
@Controller
public class LongueurController {
	
	final static Logger logger = LogManager.getLogger(Level.ALL);
	
	// injections repositories
	/** The longueur repository. */
	@Autowired
	private LongueurRepository longueurRepository;
	
	/** The voie repository. */
	@Autowired
	private VoieRepository voieRepository;
	
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	/** The secteur repository. */
	@Autowired
	private SecteurRepository secteurRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	/**
	 * Longueur voie.display the voie's longueurs
	 *
	 * @param model the model
	 * @param idVoie the id voie
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @return the voie
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur")
	public String longueurVoie(Model model, @PathVariable("idVoie") long idVoie, @PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur){
		
		Voie voie = voieRepository.findById(idVoie).get();		
		model.addAttribute("voie", voie);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		logger.info("un utilisateur consulte les longueurs de la voie "+voie.getNomDeVoie()+" id n°"+voie.getIdVoie());
		
		return "voie";
	}
	
	/*============== #Création ======================*/
	/**
	 * Form long.create a new longueur
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @param idVoie the id voie
	 * @param longueurForm the longueur form
	 * @return the formLongueur
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/create")
	public String formLong(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade,@PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @ModelAttribute("longueurForm") LongueurForm longueurForm) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Voie voie = voieRepository.findById(idVoie).get();
		model.addAttribute("voie", voie);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demander un formulaire de création de longueur pour la voie n°"+voie.getIdVoie());
		
		return "formLongueur";
	}
	
	/**
	 * Ajouter longueur. save a new longueur
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @param idVoie the id voie
	 * @param longueurForm the longueur form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the voie
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/create")
	public String ajouterLongueur(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade,@PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @ModelAttribute("longueurForm") LongueurForm longueurForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Voie voie = voieRepository.findById(idVoie).get();
		model.addAttribute("voie", voie);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
				
			if (result.hasErrors()) {
				model.addAttribute("longueurForm", longueurForm);
				return "formLongueur";
			}
		else {
			Longueur newLongueur = new Longueur();
			newLongueur.setDistance(longueurForm.getDistance());
			newLongueur.setHauteur(longueurForm.getHauteur());
			Voie voies = voieRepository.findById(idVoie).get();
			newLongueur.setVoie(voies);
			longueurRepository.save(newLongueur);
			logger.info("l'utilisateur "+usr.getPseudo()+" a créer une nouvelle longueur n°"+newLongueur.getIdLongueur()+" pour la voie n°"+voie.getIdVoie());
		}
		return"redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+ idVoie;	
	}
	
	/*============== #Modification ======================*/
	/**
	 * Edits the secteur.modify a longueur's properties
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @param idVoie the id voie
	 * @param idLongueur the id longueur
	 * @param model the model
	 * @return the editFormLongueur
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/{idLongueur}/edit")
	public String editSecteur(@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @PathVariable("idLongueur") long idLongueur, Model model) {
		
			UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("usr", usr);
		
			Longueur longueur = longueurRepository.findById(idLongueur).get();
			
			LongueurForm longForm = new LongueurForm();
			longForm.setDistance(longueur.getDistance());
			longForm.setHauteur(longueur.getHauteur());
			model.addAttribute("longueurForm", longForm);
			
			logger.info("l'utilisateur "+usr.getPseudo()+" a demander un formulaire de modification pour la longueur n°"+idLongueur);
			
		return"editFormLongueur";
	}
	
	/**
	 * Edits the longueur.save a longueur's modifications
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @param idVoie the id voie
	 * @param idLongueur the id longueur
	 * @param model the model
	 * @param longueurForm the longueur form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the voie
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/{idLongueur}/update")
	public String editLongueur(@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie, @PathVariable("idLongueur") long idLongueur, Model model, @ModelAttribute("longueurForm") LongueurForm longueurForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		if (result.hasErrors()) {
			model.addAttribute("longueurForm", longueurForm);
			return "editFormLongueur";

		}else {
		Longueur longr = longueurRepository.findById(idLongueur).get();
		longr.setDistance(longueurForm.getDistance());
		longr.setHauteur(longueurForm.getHauteur());
		longueurRepository.save(longr);
		logger.info("l'utilisateur "+usr.getPseudo()+" a enregistrer les modifications apportées pour la longueur n°"+longueurForm.getIdLongueur());
		}
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+ idVoie;
	}
	
	/*============== #Suppression ======================*/
	/**
	 * Delete longueur.delete a longueur
	 *
	 * @param idLongueur the id longueur
	 * @param idVoie the id voie
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the voie
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/longueur/{idLongueur}/delete")
	public String deleteLongueur(@PathVariable("idLongueur") long idLongueur, @PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model, final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		longueurRepository.deleteById(idLongueur);
		
		logger.warn("l'utilisateur "+usr.getPseudo()+" a supprimer la longueur n°"+idLongueur);
		
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+ idVoie;
	}
	

}
