package com.oc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.forms.TopoForm;
import com.oc.metier.TopoService;

@Controller
@RequestMapping("/formTopo")
public class TopoController {
	
	@Autowired
	private TopoService topoService;
	
	@GetMapping
	public String formTop() {
		return "formTopo";
	}
	
	@PostMapping
	public String ajouterTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		topoService.saveTopoForm(topoForm, result);
		
		return"redirect:/formTopo";
	}

}