package com.oc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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

// TODO: Auto-generated Javadoc
/**
 * The Class UserGrimpController.
 */
@Controller
public class UserGrimpController {
	
	/** The user grimp repository. */
	// injections repositories	
	@Autowired
	private UserGrimpRepository userGrimpRepository;	
	
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;	
	
	/** The topo repository. */
	@Autowired
	private TopoRepository topoRepository;
	
	// get and post Mapping
	/**
	 * Consulter profil.
	 *
	 * @param model the model
	 * @return the string
	 */
	/*============== #Pages ======================*/
	@GetMapping("/profil")
	public String consulterProfil(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<SiteEscalade> site = siteEscaladeRepository.findByUserGrimp(usr.getIdUserGrimp());
		model.addAttribute("sitList", site);
		
		Iterable<Topo> top = topoRepository.findByUserG(usr.getIdUserGrimp());
		model.addAttribute("topList", top);
		
		return "user_page";
	}
	
	/**
	 * Form insc.
	 *
	 * @param model the model
	 * @return the string
	 */
	/*============== #Création ======================*/
	@GetMapping("/inscription")
	public String formInsc(Model model) {
		
		UserGrimpForm usr = new UserGrimpForm();

		model.addAttribute("userGrimpForm", usr);
		
		return "formInscription";
		
	}
	
	/**
	 * Ajouter user grimp.
	 *
	 * @param model the model
	 * @param userGrimpForm the user grimp form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@PostMapping("/inscription")
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimpForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			model.addAttribute("userGrimpForm", userGrimpForm);
			return "formInscription";
		} 
		else if (userGrimpRepository.findByPseudo(userGrimpForm.getUsername().toLowerCase()) !=null) {
			
			result.rejectValue("username", "user.pseudo", "ce pseudo est déjà utilisé :(");
			model.addAttribute("userGrimpForm", userGrimpForm);
			
			return "formInscription";
			
		}else if(userGrimpRepository.getUsrEmail(userGrimpForm.getEmail().toLowerCase()) !=null ) {
			
			result.rejectValue("email", "user.email", "cet e-mail est déjà associé à un compte utilisateur :(");
			model.addAttribute("userGrimpForm", userGrimpForm);
			return "formInscription";
			
		}else if(userGrimpForm.getUsername().length() < 2 && userGrimpForm.getUsername().length() > 30 ||
				userGrimpForm.getPassword().length() < 4){
			
			model.addAttribute("userGrimpForm", userGrimpForm);	
			return "formInscription";
			
		}else {
		UserGrimp newUserGrimp = new UserGrimp();
		newUserGrimp.setPseudo(userGrimpForm.getUsername().toLowerCase());
		newUserGrimp.setEmail(userGrimpForm.getEmail().toLowerCase());
		newUserGrimp.setPassword(userGrimpForm.getPassword());
		userGrimpRepository.save(newUserGrimp);
		}

			return "redirect:/connexion";	
	}
	
	
	/**
	 * Edits the profil.
	 *
	 * @param model the model
	 * @param userGrimpf the user grimpf
	 * @return the string
	 */
	/*============== #Modification ======================*/
	@GetMapping("/profil/edit")
	public String editProfil(Model model, @ModelAttribute("userForm") UserGrimpForm userGrimpf) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserGrimpForm userForm = new UserGrimpForm();
		userForm.setUsername(usr.getPseudo());
		userForm.setEmail(usr.getEmail());
		userForm.setPassword(usr.getPassword());
		model.addAttribute("userForm", userForm);
		
		return "editFormInscription";
	}
	
	/**
	 * Update profil.
	 *
	 * @param model the model
	 * @param userGrimpf the user grimpf
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the string
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
			
		}else if (ur !=null && ur.getIdUserGrimp() != usr.getIdUserGrimp()) {
					
			result.rejectValue("username", "user.name", "ce pseudo est déjà utilisé :(");
			model.addAttribute("userForm", userGrimpf);
			
			return "editFormInscription";
			
			
		}else if (uRm.getEmail() != null && uRm.getIdUserGrimp() != usr.getIdUserGrimp()) {
			
			result.rejectValue("email", "user.email", "cet e-mail est déjà associé à un compte utilisateur :(");
			model.addAttribute("userForm", userGrimpf);
			
			return "editFormInscription";
		}		
		else {
		usr.setPseudo(userGrimpf.getUsername().toLowerCase());
		usr.setEmail(userGrimpf.getEmail().toLowerCase());
		usr.setPassword(userGrimpf.getPassword());
		userGrimpRepository.save(usr);
		}
		return "redirect:/profil";
		
	}
	
	/**
	 * Delete user.
	 *
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	/*============== #Suppression ======================*/
	@GetMapping("/profil/delete")
	public String deleteUser(final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userGrimpRepository.deleteById(usr.getIdUserGrimp());
		
		return "redirect:/index";
	}	
	
}
