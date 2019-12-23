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
	@GetMapping("/profil/{idUserGrimp}")
	public String consulterProfil(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		UserGrimp usr = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", usr);
		
		Iterable<SiteEscalade> site = siteEscaladeRepository.findByUserGrimp(idUserGrimp);
		model.addAttribute("sitList", site);
		
		Iterable<Topo> top = topoRepository.findByUserG(idUserGrimp);
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
	@GetMapping("/profil/{idUserGrimp}/edit")
	public String editProfil(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		UserGrimp usr = userGrimpRepository.findById(idUserGrimp).get();
		UserGrimpForm userForm = new UserGrimpForm();
		userForm.setUsername(usr.getPseudo());
		userForm.setEmail(usr.getEmail());
		userForm.setPassword(usr.getPassword());
		model.addAttribute("userForm", userForm);
		
		return "editFormInscription";
	}
	
	@PostMapping("/profil/{idUserGrimp}/update")
	public String updateProfil(@PathVariable("idUserGrimp") long idUserGrimp, @ModelAttribute("userGrimp") UserGrimpForm userGrimpForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "editFormInscription";
		}else {
		UserGrimp usr = userGrimpRepository.findById(idUserGrimp).get();
		usr.setPseudo(userGrimpForm.getUsername());
		usr.setEmail(userGrimpForm.getEmail());
		usr.setPassword(userGrimpForm.getPassword());
		userGrimpRepository.save(usr);
		}
		return "redirect:/profil/"+idUserGrimp;
		
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/profil/{idUserGrimp}/delete")
	public String deleteUser(@PathVariable("idUserGrimp") long idUserGrimp, final RedirectAttributes redirectAttributes) {
		
		userGrimpRepository.deleteById(idUserGrimp);
		
		return "redirect:/index";
	}	

}
