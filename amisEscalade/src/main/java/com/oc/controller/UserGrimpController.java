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
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.SiteEscalade;
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
		
		return "user_page";
	}
	

}
