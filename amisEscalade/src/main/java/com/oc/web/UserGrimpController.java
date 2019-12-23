package com.oc.web;

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

import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.UserGrimpForm;

@Controller
public class UserGrimpController {
	
	// injections repositories	
	@Autowired
	private UserGrimpRepository userGrimpRepository;	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;	
	@Autowired
	private TopoRepository topoRepository;
	
	// get and post Mapping
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
	
	/*============== #Création ======================*/
	@GetMapping("/inscription")
	public String formInsc() {
		return "formInscription";
	}
	
	@PostMapping("/inscription")
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimpForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formInscription";
		}else {
		UserGrimp newUserGrimp = new UserGrimp();
		newUserGrimp.setPseudo(userGrimpForm.getUsername());
		newUserGrimp.setEmail(userGrimpForm.getEmail());
		newUserGrimp.setPassword(userGrimpForm.getPassword());
		userGrimpRepository.save(newUserGrimp);
		}
			return "redirect:/index";	
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/profil/edit")
	public String editProfil(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserGrimpForm userForm = new UserGrimpForm();
		userForm.setUsername(usr.getPseudo());
		userForm.setEmail(usr.getEmail());
		userForm.setPassword(usr.getPassword());
		model.addAttribute("userForm", userForm);
		
		return "editFormInscription";
	}
	
	@PostMapping("/profil/update")
	public String updateProfil(@ModelAttribute("userGrimp") UserGrimpForm userGrimpForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "editFormInscription";
		}else {
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usr.setPseudo(userGrimpForm.getUsername());
		usr.setEmail(userGrimpForm.getEmail());
		usr.setPassword(userGrimpForm.getPassword());
		userGrimpRepository.save(usr);
		}
		return "redirect:/profil";
		
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/profil/delete")
	public String deleteUser(final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userGrimpRepository.deleteById(usr.getIdUserGrimp());
		
		return "redirect:/index";
	}	

}
