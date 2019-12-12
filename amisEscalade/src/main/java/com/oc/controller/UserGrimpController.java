package com.oc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.UserGrimpForm;
import com.oc.metier.UserGrimpService;

@Controller
public class UserGrimpController {
	
	@Autowired
	private UserGrimpService userGrimpService;
	
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	@Autowired
	private TopoRepository topoRepository;
	
	@GetMapping("/inscription")
	public String formInsc() {
		return "formInscription";
	}
	

	@PostMapping("/inscription")
	public String ajouterUserGrimp(Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimp,
			final RedirectAttributes redirectAttributes) {

		userGrimpService.saveUserGrimpForm(userGrimp);

			return "redirect:/index";			
		
	}
	
	@GetMapping("/profil/{idUserGrimp}")
	public String consulterProfil(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		UserGrimp usr = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", usr);
		
		List<SiteEscalade> site = siteEscaladeRepository.findByUserGrimp(idUserGrimp);
		model.addAttribute("sitList", site);
		
		List<Topo> top = topoRepository.findByUserG(idUserGrimp);
		model.addAttribute("topList", top);
		
		return "user_page";
	}
	
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
	public String updateProfil(@PathVariable("idUserGrimp") long idUserGrimp, @ModelAttribute("userGrimp") UserGrimpForm userGrimpForm, 
			final RedirectAttributes redirectAttributes) {
		
		userGrimpService.modifyProfil(idUserGrimp, userGrimpForm);
		
		return "redirect:/profil/"+idUserGrimp;
		
	}
	
	@GetMapping("/profil/{idUserGrimp}/delete")
	public String deleteUser(@PathVariable("idUserGrimp") long idUserGrimp, final RedirectAttributes redirectAttributes) {
		
		userGrimpRepository.deleteById(idUserGrimp);
		
		return "redirect:/index";
	}
	

}
