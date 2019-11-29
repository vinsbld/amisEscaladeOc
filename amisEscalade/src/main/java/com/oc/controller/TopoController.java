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

import com.oc.dao.TopoRepository;
import com.oc.entities.Topo;
import com.oc.forms.TopoForm;
import com.oc.metier.TopoService;

@Controller
public class TopoController {
	
	@Autowired
	private TopoService topoService;
	
	@Autowired
	private TopoRepository topoRepository;
	
	@GetMapping("/topo")
	public String topoPage(Model model) {
		
		List<Topo> topo = topoRepository.findAll();
		model.addAttribute("listeTopo", topo);
		
		return "topo";
	}
	
	@GetMapping("/formTopo")
	public String formTop() {
		return "formTopo";
	}
	
	@PostMapping("/formTopo")
	public String ajouterTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		topoService.saveTopoForm(topoForm, result);
		
		return"redirect:/topo";
	}
	

}
