package com.oc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.TopoForm;
import com.oc.metier.TopoService;

@Controller
public class TopoController {
	
	@Autowired
	private TopoService topoService;
	
	@Autowired
	private TopoRepository topoRepository;
	
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	@GetMapping("/topo")
	public String topoPage(Model model) {
		
		List<Topo> topo = topoRepository.findAll();
		model.addAttribute("listeTopo", topo);
		
		return "topo";
	}
	
	@GetMapping("/formTopo/{idUserGrimp}/create")
	public String formTop(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		UserGrimp userG = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", userG);
		
		return "formTopo";
	}
	
	@PostMapping("/formTopo/{idUserGrimp}/create")
	public String ajouterTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, @PathVariable("idUserGrimp") long idUserGrimp, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		Topo newTopo = new Topo();
		newTopo.setDescription(topoForm.getDescription());
		newTopo.setName(topoForm.getName());
		newTopo.setLieu(topoForm.getLieu());
		newTopo.setEdate(topoForm.getEdate());
		newTopo.setDispo(topoForm.getDispo());
		
		UserGrimp userG = userGrimpRepository.findById(idUserGrimp).get();
		newTopo.setUserGrimp(userG);
		
		topoRepository.save(newTopo);
		
		return "redirect:/profil/"+idUserGrimp;
	}
	
	@GetMapping("topo/{idTopo}/edit")
	public String editTopo(@PathVariable("idTopo") long idTopo, Model model) {
		 
		Topo topo = topoRepository.findById(idTopo).get();
		model.addAttribute("topo", topo);
		
		return "editFormTopo";
	}
	
	@PostMapping("topo/{idTopo}/update")
	public String updateTopo(@PathVariable("idTopo") long idTopo, Model model, @ModelAttribute("editFormTopo") TopoForm topoForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		topoService.modifyTopo(idTopo, topoForm, result);
		
		return"redirect:/topo";
	}
	
	@GetMapping("/topo/{idTopo}/delete")
	public String deleteTopo(@PathVariable("idTopo") long idTopo, Model model, final RedirectAttributes redirectAttributes) {
	
		topoRepository.deleteById(idTopo);
	
		return"redirect:/topo";
	}
	
	@GetMapping("/le_topo/{idTopo}/view")
	public String leTopo(Model model, @PathVariable("idTopo") long idTopo) {
		
		Topo topo = topoRepository.findById(idTopo).get();
		model.addAttribute("topo", topo);
		
		return"le_Topo";
		
	}
	

}
