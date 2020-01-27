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
import com.oc.dao.RatingRepository;
import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Rating;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.UserGrimp;
import com.oc.entities.Voie;
import com.oc.forms.VoieForm;

/**
 * The Class VoieController.
 */
@Controller
public class VoieController {
	
	final static Logger logger = LogManager.getLogger(Level.ALL);

	// injections repositories
	
	/** The voie repository. */
	@Autowired
	private VoieRepository voieRepository;
	
	/** The secteur repository. */
	@Autowired
	private SecteurRepository secteurRepository;	
	
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	/** The longueur repository. */
	@Autowired 
	private LongueurRepository longueurRepository;
	
	/** The rating repository. */
	@Autowired
	private RatingRepository ratingRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	
	/**
	 * Voie secteur. display the secteur's voies
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idSecteur the id secteur
	 * @param idVoie the id voie
	 * @return the voie page
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}")
	public String voieSecteur(Model model,@PathVariable("idSiteEscalade") long idSiteEscalade, @PathVariable("idSecteur") long idSecteur, @PathVariable("idVoie") long idVoie){
	
	SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
	model.addAttribute("site", site);
		
	Secteur secteur= secteurRepository.findById(idSecteur).get();
	model.addAttribute("secteur", secteur);
	
	Voie voie = voieRepository.findById(idVoie).get();
	model.addAttribute("voie", voie);
	
	Iterable<Longueur> longueur = longueurRepository.findByVoie(idVoie);
	model.addAttribute("longueur", longueur);
	
	logger.info("Un utilisateur consulte la liste des voies appartenant au secteur "+secteur.getNomDuSecteur()+" id : "+secteur.getIdSecteur());
	
	return "voie";
	}
	
	/*============== #Création ======================*/
	
	/**
	 * Form voie.display the form for create a new voie
	 *
	 * @param model the model
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @return the voie form
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/create")
	public String formVoie(Model model, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
			
		Iterable<Rating> rate = ratingRepository.findAll();
		model.addAttribute("rate", rate);
		
		VoieForm voieForm = new VoieForm();
		model.addAttribute("voieForm", voieForm);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un formulaire de création de voie pour le secteur "+secteur.getNomDuSecteur()+" id : "+secteur.getIdSecteur());
		
		return "formVoie";
	}
	
	/**
	 * Ajouter voie.save the new voie
	 *
	 * @param model the model
	 * @param voieForm the voie form
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the voie page
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/create")
	public String ajouterVoie(Model model, @ModelAttribute("voieForm") VoieForm voieForm, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
			
		Iterable<Rating> rate = ratingRepository.findAll();
		model.addAttribute("rate", rate);
		
		if (result.hasErrors()) {
			return "formVoie";
		}
		else if (voieForm.getName().isBlank()) {
			result.rejectValue("name", "nameLength.value", "Le nom de la voie ne doit pas être vide !");
			model.addAttribute("voieForm", voieForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de voie vide");
			return "formVoie";
		}
		else if (voieForm.getName().length()>25) {
			result.rejectValue("name", "nameLength.value", "Le nom de la voie ne doit pas dépasser 25 caractères !");
			model.addAttribute("voieForm", voieForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de voie de plus de 25 caractères");
			return "formVoie";
		}
		else {
		Voie newVoie = new Voie();
		newVoie.setNomDeVoie(voieForm.getName());
		newVoie.setCotation(voieForm.getCotation());
		Secteur voieSec = secteurRepository.findById(idSecteur).get();
		newVoie.setSecteur(voieSec);
		voieRepository.save(newVoie);
		logger.error("l'utilisateur "+usr.getPseudo()+" a créer une nouvelle voie "+newVoie.getNomDeVoie()+" id : "+newVoie.getIdVoie());
		}
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}
	
	/*============== #Modification ======================*/
	
	/**
	 * Edits the voie. modify an existing voie 
	 *
	 * @param idVoie the id voie
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @return the edit form voie
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/edit")
	public String editVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {

		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
	  
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		Iterable<Rating> rate = ratingRepository.findAll();
		model.addAttribute("rate", rate);
		
		Voie voie = voieRepository.findById(idVoie).get();
		model.addAttribute("voie", voie);
		
		VoieForm voiForm = new VoieForm();
		voiForm.setIdVoie(voie.getIdVoie());
		voiForm.setName(voie.getNomDeVoie());
		voiForm.setCotation(voie.getCotation());
		model.addAttribute("voieForm", voiForm);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un formulaire de modification pour la voie "+voie.getNomDeVoie()+" id : "+voie.getIdVoie());
		
		return"editFormVoie";
	}
	
	/**
	 * Update voie.save the voie's modifications 
	 *
	 * @param idVoie the id voie
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @param voieForm the voie form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the voie page
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/update")
	public String updateVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model, 
			@ModelAttribute("voieForm") VoieForm voieForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
	  
		Secteur secteur = secteurRepository.findById(idSecteur).get();
		model.addAttribute("secteur", secteur);
		
		Iterable<Rating> rate = ratingRepository.findAll();
		model.addAttribute("rate", rate);
		
		Voie voie = voieRepository.findById(idVoie).get();
		model.addAttribute("voie", voie);
		
		if (result.hasErrors()) {
			return "editFormVoie";
		}
		else if (voieForm.getName().isBlank()) {
			result.rejectValue("name", "nameLength.value", "Le nom de la voie ne doit pas être vide !");
			model.addAttribute("voieForm", voieForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de voie vide ");
			return "editFormVoie";
		}
		else if (voieForm.getName().length()>25) {
			result.rejectValue("name", "nameLength.value", "Le nom de la voie ne doit pas dépasser 25 caractères !");
			model.addAttribute("voieForm", voieForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de voie de plus de 25 caractères ");
			return "editFormVoie";
		}
		else {
		voie.setNomDeVoie(voieForm.getName());
		voie.setCotation(voieForm.getCotation());
		voieRepository.save(voie);
		logger.info("l'utilisateur "+usr.getPseudo()+" a enregistré les modifications faites sur la voie n°"+voie.getIdVoie());
		}
		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur+"/voie/"+idVoie;	
	}
	
	/*============== #Suppression ======================*/
	
	/**
	 * Delete voie.
	 *
	 * @param idVoie the id voie
	 * @param idSecteur the id secteur
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the secteur page
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/secteur/{idSecteur}/voie/{idVoie}/delete")
	public String deleteVoie(@PathVariable("idVoie") long idVoie, @PathVariable("idSecteur") long idSecteur, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model,
			final RedirectAttributes redirectAttributes) {

		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		logger.warn("l'utilisateur "+usr.getPseudo()+" a supprimer la voie n°"+idVoie);
		voieRepository.deleteById(idVoie);

		return "redirect:/site_escalade/"+idSiteEscalade+"/secteur/"+idSecteur;
	}

}