package com.oc.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.oc.dao.UserGrimpRepository;
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
	private UserGrimpRepository userGrimpRepository;
	@Autowired
	private CodexRepository codexRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/topo/{idUserGrimp}")
	public String topoPage(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		List<Topo> topo = topoRepository.findAll();
		model.addAttribute("listeTopo", topo);
		
		UserGrimp userG = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", userG);
		
		return "topo";
	}
	
	@GetMapping("/le_topo/{idTopo}/{idUserGrimp}/view")
	public String leTopo(Model model, @PathVariable("idTopo") long idTopo, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		Topo topo = topoRepository.findById(idTopo).get();
		model.addAttribute("topo", topo);
		
		UserGrimp usr = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", usr);
		
		return"le_Topo";
		
	}
	
	/*============== #Création ======================*/
	@GetMapping("/formTopo/{idUserGrimp}/create")
	public String formTop(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		UserGrimp userG = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", userG);
		
		Iterable<Codex> cdxList = codexRepository.findAll();
		model.addAttribute("cdxList", cdxList);
		
		return "formTopo";
	}
	
	@PostMapping("/formTopo/{idUserGrimp}/create")
	public String ajouterTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, @PathVariable("idUserGrimp") long idUserGrimp, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "formTopo";
		}else {
		Topo newTopo = new Topo();
		newTopo.setDescription(topoForm.getDescription());
		newTopo.setName(topoForm.getName());
		newTopo.setLieu(topoForm.getLieu());
		newTopo.setEdate(topoForm.getEdate());
		newTopo.setDispo(topoForm.getDispo());
		UserGrimp userG = userGrimpRepository.findById(idUserGrimp).get();
		newTopo.setUserGrimp(userG);
		topoRepository.save(newTopo);
		}
		return "redirect:/profil/"+idUserGrimp;
	}
	
	/*============== #Modification ======================*/
	@GetMapping("/topo/{idTopo}/{idUserGrimp}/edit")
	public String editTopo(@PathVariable("idTopo") long idTopo, Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
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
	
	@PostMapping("/topo/{idTopo}/{idUserGrimp}/update")
	public String updateTopo(@PathVariable("idTopo") long idTopo, @PathVariable("idUserGrimp") long idUserGrimp, Model model, @ModelAttribute("editFormTopo") TopoForm topoForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "editFormTopo";
		}else {
		Topo topo = topoRepository.findById(idTopo).get();
		topo.setName(topoForm.getName());
		topo.setDescription(topoForm.getDescription());
		topo.setLieu(topoForm.getLieu());
		topo.setEdate(topoForm.getEdate());
		topo.setDispo(topoForm.getDispo());
		topoRepository.save(topo);
		}
		return "redirect:/profil/"+idUserGrimp;
	}
	
	/*============== #Suppression ======================*/
	@GetMapping("/topo/{idTopo}/{idUserGrimp}/delete")
	public String deleteTopo(@PathVariable("idTopo") long idTopo, Model model, @PathVariable("idUserGrimp") long idUserGrimp, final RedirectAttributes redirectAttributes) {
	
		topoRepository.deleteById(idTopo);
	
		return "redirect:/profil/"+idUserGrimp;
	}
	
	/*============== #Reservations ======================*/
	@GetMapping("/topo/{idUserGrimp}/mes_reservations")
	public String topoResa(Model model, @PathVariable("idUserGrimp") long idUserGrimp) {
		
		UserGrimp usr = userGrimpRepository.findById(idUserGrimp).get();
		model.addAttribute("usr", usr);
		
		Iterable<Topo> top = topoRepository.findByUserG(idUserGrimp);
		model.addAttribute("topList", top);
		
		return "reservation_topo";
	}

}
