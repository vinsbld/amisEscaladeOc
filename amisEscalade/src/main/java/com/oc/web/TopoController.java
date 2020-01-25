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
import com.oc.dao.ReservationRepository;
import com.oc.dao.TopoRepository;
import com.oc.entities.Codex;
import com.oc.entities.Reservation;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.TopoForm;

/**
 * The Class TopoController.
 */
@Controller
public class TopoController {
	
	// injections repositories
	
	/** The topo repository. */
	@Autowired
	private TopoRepository topoRepository;
	
	/** The codex repository. */
	@Autowired
	private CodexRepository codexRepository;
	
	/** The reservation repository. */
	@Autowired
	private ReservationRepository reservationRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	
	/**
	 * Topo page.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/topo")
	public String topoPage(Model model) {
		
		List<Topo> topo = topoRepository.findAll();
		model.addAttribute("listeTopo", topo);
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		return "topo";
	}
	
	/**
	 * Le topo.
	 *
	 * @param model the model
	 * @param idTopo the id topo
	 * @return the string
	 */
	@GetMapping("/le_topo/{idTopo}/view")
	public String leTopo(Model model, @PathVariable("idTopo") long idTopo) {
		
		Topo topo = topoRepository.findById(idTopo).get();
		model.addAttribute("topo", topo);
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		List<Reservation> r = reservationRepository.getDemandeEncours(usr.getIdUserGrimp(), idTopo);
		model.addAttribute("ListDemandes", r);
		
		return"le_Topo";
		
	}
	
	/*============== #Création ======================*/
	
	/**
	 * Form top.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/formTopo/create")
	public String formTop(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		TopoForm topForm = new TopoForm();
		model.addAttribute("topoForm", topForm);
		
		return "formTopo";
	}
	
	/**
	 * Ajouter topo.
	 *
	 * @param model the model
	 * @param topoForm the topo form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@PostMapping("/formTopo/create")
	public String ajouterTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		if (result.hasErrors()) {
			model.addAttribute("topoForm", topoForm);
			return "formTopo";	
		}
		else if (topoForm.getName().isBlank() || topoForm.getName().length()>25) {
			result.rejectValue("name", "nameLength.value", "le nom du topo ne doit pas être vide ou dépasser 25 caractères ! ");
			model.addAttribute("topoForm", topoForm);
			return "formTopo";
		}
		else if(topoForm.getDescription().length()>255 || topoForm.getDescription().isBlank()) {
			result.rejectValue("description", "descriptionLength.value", "votre description ne doit pas être vide ou dépasser 255 caractères !");
			model.addAttribute("topoForm", topoForm);
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
	
	/**
	 * Edits the topo.
	 *
	 * @param idTopo the id topo
	 * @param model the model
	 * @return the string
	 */
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
		model.addAttribute("topoForm", tpoFrm);
		
		return "editFormTopo";
	}
	
	/**
	 * Update topo.
	 *
	 * @param idTopo the id topo
	 * @param model the model
	 * @param topoForm the topo form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@PostMapping("/topo/{idTopo}/update")
	public String updateTopo(@PathVariable("idTopo") long idTopo, Model model, @ModelAttribute("topoForm") TopoForm topoForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		topoRepository.getTopoName(topoForm.getName());
		
		if (result.hasErrors()) {
			model.addAttribute("topoForm", topoForm);
			return "editFormTopo";
			}
			else if (topoForm.getName().isBlank() || topoForm.getName().length()>25) {
				result.rejectValue("name", "nameLength.value", "le nom du topo ne doit pas être vide ou dépasser 25 caractères !");
				model.addAttribute("topoForm", topoForm);
				return "editFormTopo";
			}
			else if (topoForm.getDescription().length()>255 || topoForm.getDescription().isBlank()) {
				result.rejectValue("description", "descriptionLength.value", "Votre description ne doit être vide ou dépasser 255 caractères !");
				model.addAttribute("topoForm", topoForm);
				return "editFormTopo";
			}
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
	
	/**
	 * Delete topo.
	 *
	 * @param idTopo the id topo
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@GetMapping("/topo/{idTopo}/delete")
	public String deleteTopo(@PathVariable("idTopo") long idTopo, Model model, final RedirectAttributes redirectAttributes) {
	
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		topoRepository.deleteById(idTopo);
	
		return "redirect:/profil";
	}

}
