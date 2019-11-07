package com.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.classes.UserGrimpForm;
import com.oc.dao.UserGrimpRepository;

@Controller
public class UserGrimpController {

	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	@PostMapping("/inscription")
	public String saveInscription (Model model, @ModelAttribute("userGrimpForm") UserGrimpForm userGrimpForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		UserGrimpForm newUserGrimp = new UserGrimpForm();
		newUserGrimp.setUsername(userGrimpForm.getUsername());
		newUserGrimp.setEmail(userGrimpForm.getEmail());
		newUserGrimp.setPassword(userGrimpForm.getPassword());
		
		userGrimpRepository.save(newUserGrimp);
		
		return "redirect:/formInscription";
	}
	
}
