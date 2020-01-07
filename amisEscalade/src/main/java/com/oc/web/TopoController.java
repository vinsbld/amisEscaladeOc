package com.oc.web;

import java.util.List;
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

import com.oc.dao.CodexRepository;
import com.oc.dao.TopoRepository;
import com.oc.entities.Codex;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.TopoForm;

@Controller
public class TopoController {
	
	// injections repositories
	@Autowired
	private TopoRepository topoRepository;
	@Autowired
	private CodexRepository codexRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/topo")
	public String topoPage(Model model) {
		
		List<Topo> topo = topoRepository.findAll();
		model.addAttribute("listeTopo", topo);
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		return "topo";
	}
	
	@GetMapping("/le_topo/{idTopo}/view")
	public String leTopo(Model model, @PathVariable("idTopo") long idTopo) {
		
		Topo topo = topoRepository.findById(idTopo).get();
		model.addAttribute("topo", topo);
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		return"le_Topo";
		
	}
	
	/*============== #Création ======================*/
	@GetMapping("/formTopo/create")
	public String formTop(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		return "formTopo";
	}
	
	@PostMapping("/formTopo/create")
	public String ajouterTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formTopo";	
		}
		else {
		Topo newTopo = new Topo();
		newTopo.setDescription(topoForm.getDescription());
		newTopo.setName(topoForm.getName());
		newTopo.setLieu(topoForm.getLieu());
		newTopo.setEdate(topoForm.getEdate());
		newTopo.setDispo(topoForm.getDispo());
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		newTopo.setUserGrimp(usr);
		topoRepository.save(newTopo);
		}
		return "redirect:/profil";
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/topo/{idTopo}/edit")
	public String editTopo(@PathVariable("idTopo") long idTopo, Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		Topo topo = topoRepository.findById(idTopo).get();
		TopoForm tpoFrm = new TopoForm();
		tpoFrm.setName(topo.getName());
		tpoFrm.setDescription(topo.getDescription());
		tpoFrm.setLieu(topo.getLieu());
		tpoFrm.setEdate(topo.getEdate());
		tpoFrm.setDispo(topo.getDispo());
		model.addAttribute("topo", tpoFrm);
		
		return "editFormTopo";
	}
	
	@PostMapping("/topo/{idTopo}/update")
	public String updateTopo(@PathVariable("idTopo") long idTopo, Model model, @ModelAttribute("editFormTopo") TopoForm topoForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		Topo tpo = topoRepository.getTopoName(topoForm.getName());
		
		if (result.hasErrors()) {
			return "editFormTopo";
			}
		/*
		 * else if(tpo != null && tpo.getIdTopo() != idTopo) { return "formTopo"; }
		 */
		else {
		Topo topo = topoRepository.findById(idTopo).get();
		topo.setName(topoForm.getName());
		topo.setDescription(topoForm.getDescription());
		topo.setLieu(topoForm.getLieu());
		topo.setEdate(topoForm.getEdate());
		topo.setDispo(topoForm.getDispo());
		topoRepository.save(topo);
		}
		return "redirect:/profil";
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/topo/{idTopo}/delete")
	public String deleteTopo(@PathVariable("idTopo") long idTopo, Model model, final RedirectAttributes redirectAttributes) {
	
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		topoRepository.deleteById(idTopo);
	
		return "redirect:/profil";
	}

}
