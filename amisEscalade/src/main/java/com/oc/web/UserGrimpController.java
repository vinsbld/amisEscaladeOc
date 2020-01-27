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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.UserGrimpForm;

/**
 * The Class UserGrimpController.
 */
@Controller
public class UserGrimpController {
	
	final static Logger logger = LogManager.getLogger(Level.ALL);
	
	/*============== #injections repositories ======================*/
	/** The user grimp repository. */
	@Autowired
	private UserGrimpRepository userGrimpRepository;	
	
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;	
	
	/** The topo repository. */
	@Autowired
	private TopoRepository topoRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	/**
	 * Consulter profil.
	 *
	 * @param model the model
	 * @return the user_page
	 */
	@GetMapping("/profil")
	public String consulterProfil(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<SiteEscalade> site = siteEscaladeRepository.findByUserGrimp(usr.getIdUserGrimp());
		model.addAttribute("sitList", site);
		
		Iterable<Topo> top = topoRepository.findByUserG(usr.getIdUserGrimp());
		model.addAttribute("topList", top);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" consulte son profil");
		
		return "user_page";
	}
	
	/*============== #Création ======================*/
	/**
	 * Form insc.create a new account
	 *
	 * @param model the model
	 * @return the formInscription
	 */
	@GetMapping("/inscription")
	public String formInsc(Model model) {
		
		UserGrimpForm usr = new UserGrimpForm();
		model.addAttribute("userGrimpForm", usr);
		
		logger.info("Un utilisateur a demandé un formulaire d'inscription");
		
		return "formInscription";		
	}
	
	/**
	 * Ajouter user grimp.save a new user account 
	 *
	 * @param model the model
	 * @param userGrimpForm the user grimp form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the connexion page
	 */
	@PostMapping("/inscription")
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimpForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			model.addAttribute("userGrimpForm", userGrimpForm);
			return "formInscription";
			
		}
		else if (userGrimpForm.getUsername().isBlank()) {
			result.rejectValue("username", "pseudoLength.value", "votre pseudo doit contenir 2 caratères minimum et 30 caractères maximum !");
			model.addAttribute("userGrimpForm", userGrimpForm);
			logger.error("l'utilisateur a saisi une valeur vide pour le nom d'utilisateur");
			return "formInscription";
		}
		else if (userGrimpForm.getUsername().length()>30 || userGrimpForm.getUsername().length()<2) {
			result.rejectValue("username", "pseudoLength.value", "votre pseudo doit contenir 2 caratères minimum et 30 caractères maximum !");
			model.addAttribute("userGrimpForm", userGrimpForm);
			logger.error("l'utilisateur a saisi une valeur ne correspondant pas aux critères de longueurs de caractères max et min lors de la saisie de son pseudo");
			return "formInscription";
		}
		else if (userGrimpRepository.findByPseudo(userGrimpForm.getUsername().toLowerCase()) !=null) {
			
			result.rejectValue("username", "user.pseudo", "ce pseudo est déjà utilisé :(");
			model.addAttribute("userGrimpForm", userGrimpForm);
			logger.error("l'utilisateur a choisi un pseudo déjà utilisé par un autre utilisateur");
			return "formInscription";
			
		}else if(userGrimpRepository.getUsrEmail(userGrimpForm.getEmail().toLowerCase()) !=null ) {
			
			result.rejectValue("email", "user.email", "cet e-mail est déjà associé à un compte utilisateur :(");
			model.addAttribute("userGrimpForm", userGrimpForm);
			logger.error("l'utilisateur a choisi un email déjà utilisé pour un compte utilisateur existant");
			return "formInscription";
			
		}else if(userGrimpForm.getPassword().length() < 4){
			
			model.addAttribute("userGrimpForm", userGrimpForm);	
			logger.error("l'utilisateur a saisi un mot de passe avec moins de 4 caractères");
			return "formInscription";
			
		}else {
		UserGrimp newUserGrimp = new UserGrimp();
		newUserGrimp.setPseudo(userGrimpForm.getUsername().toLowerCase());
		newUserGrimp.setEmail(userGrimpForm.getEmail().toLowerCase());
		newUserGrimp.setPassword(userGrimpForm.getPassword());
		userGrimpRepository.save(newUserGrimp);
		logger.info("Un nouvel utilisateur s'est inscrit "+newUserGrimp.getPseudo()+" id : "+newUserGrimp.getIdUserGrimp());
		}

			return "redirect:/connexion";	
	}
	
	/*============== #Modification ======================*/
	/**
	 * Edits the profil.change user's profil informations
	 *
	 * @param model the model
	 * @param userGrimpf the user grimpf
	 * @return the editFormInscription
	 */
	@GetMapping("/profil/edit")
	public String editProfil(Model model, @ModelAttribute("userForm") UserGrimpForm userGrimpf) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserGrimpForm userForm = new UserGrimpForm();
		userForm.setUsername(usr.getPseudo());
		userForm.setEmail(usr.getEmail());
		userForm.setPassword(usr.getPassword());
		model.addAttribute("userForm", userForm);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+" a demandé un formulaire de modification de son profil");
		
		return "editFormInscription";
	}
	
	/**
	 * Update profil.save user's profil modifications 
	 *
	 * @param model the model
	 * @param userGrimpf the user grimpf
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the profil
	 */
	@PostMapping("/profil/update")
	public String updateProfil(Model model, @ModelAttribute("userForm") UserGrimpForm userGrimpf, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserGrimp ur = userGrimpRepository.findByPseudo(userGrimpf.getUsername().toLowerCase());
		UserGrimp uRm = userGrimpRepository.getUsrEmail(userGrimpf.getEmail().toLowerCase());
				
		if (result.hasErrors()) {
			model.addAttribute("userForm", userGrimpf);
			return "editFormInscription";
			
		}		
		else if (userGrimpf.getUsername().isBlank()) {
			result.rejectValue("username", "pseudoLength.value", "votre pseudo doit contenir 2 caratères minimum et 30 caractères maximum !");
			model.addAttribute("userForm", userGrimpf);
			logger.error("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+" a saisi une valeur vide pour le nom d'utilisateur");
			return "editFormInscription";
		}
		else if (userGrimpf.getUsername().length()>30 || userGrimpf.getUsername().length()<2) {
			result.rejectValue("username", "pseudoLength.value", "votre pseudo doit contenir 2 caratères minimum et 30 caractères maximum !");
			model.addAttribute("userForm", userGrimpf);
			logger.error("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+" a saisi une valeur ne correspondant pas aux critères de longueurs de caractères max et min lors de la saisie de son pseudo");
			return "editFormInscription";
		}
		else if (ur !=null && ur.getIdUserGrimp() != usr.getIdUserGrimp()) {
					
			result.rejectValue("username", "user.name", "ce pseudo est déjà utilisé :(");
			model.addAttribute("userForm", userGrimpf);
			logger.error("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+"a choisi un pseudo déjà utilisé par un autre utilisateur");
			return "editFormInscription";
			
			
		}else if (uRm.getEmail() != null && uRm.getIdUserGrimp() != usr.getIdUserGrimp()) {
			
			result.rejectValue("email", "user.email", "cet e-mail est déjà associé à un compte utilisateur :(");
			model.addAttribute("userForm", userGrimpf);
			logger.error("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+" a choisi un email déjà utilisé pour un compte utilisateur existant");
			return "editFormInscription";
		}		
		else {
		usr.setPseudo(userGrimpf.getUsername().toLowerCase());
		usr.setEmail(userGrimpf.getEmail().toLowerCase());
		usr.setPassword(userGrimpf.getPassword());
		userGrimpRepository.save(usr);
		logger.info("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+" a enregisté les modifications apportées à son profil utilisateur");
		}
		return "redirect:/profil";
		
	}
	
	/*============== #Suppression ======================*/
	/**
	 * Delete user.delete account
	 *
	 * @param redirectAttributes the redirect attributes
	 * @return the index
	 */
	@GetMapping("/profil/delete")
	public String deleteUser(final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.warn("l'utilisateur "+usr.getPseudo()+" id : "+usr.getIdUserGrimp()+" a supprimé son profil");
		userGrimpRepository.deleteById(usr.getIdUserGrimp());
		
		return "redirect:/index";
	}	
	
}
