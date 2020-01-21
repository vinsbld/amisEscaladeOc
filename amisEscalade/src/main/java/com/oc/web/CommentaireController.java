package com.oc.web;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
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

import com.oc.dao.CommentaireRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.Commentaire;
import com.oc.entities.SiteEscalade;
import com.oc.entities.UserGrimp;
import com.oc.forms.CommentaireForm;

@Controller
public class CommentaireController {
	
	// injections repositories
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site/{idSiteEscalade}/commentaire")
	public String comnt(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Commentaire> cmtr = commentaireRepository.findComBySite(site.getIdSiteEscalade());
		model.addAttribute("cmtr", cmtr);
					
		return "/commentaire_site";
	}
		
	/*============== #Création ======================*/
	@PostMapping("/commentaire/site/{idSiteEscalade}")
	public String comntR(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm, 
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		if(result.hasErrors()) {
			model.addAttribute("commentaireForm", commentaireForm);
			return "redirect:/site/"+idSiteEscalade+"/commentaire";
		}
		else if(commentaireForm.getComments()=="") {
			return "redirect:/site/"+idSiteEscalade+"/commentaire";
		}
		else if(commentaireForm.getComments().length()>255) {
			result.rejectValue("comments", "comments.value", "votre commentaire ne doit pas dépasser 255 caractères ;( ");
			model.addAttribute("commentaireForm", commentaireForm);
			return "redirect:/site/"+idSiteEscalade+"/commentaire";
		}
		else {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Commentaire newCommentaire = new Commentaire();
		newCommentaire.setSiteEscalade(site);
		newCommentaire.setUserGrimp(usr);
		newCommentaire.setComments(commentaireForm.getComments());
		newCommentaire.setDate(date);
		commentaireRepository.save(newCommentaire);
		
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
		}
	}
	private boolean isNullOrEmpty(String comments) {
		return false;
	}

	/*============== #Modification ======================*/
	@GetMapping("/commentaire/{idCom}/site/{idSiteEscalade}/edit")
	public String editComnt(Model model, @PathVariable("idSiteEscalade") Long idSiteEscalade, @PathVariable("idCom")Long idCom) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);

		Commentaire com = commentaireRepository.findById(idCom).get();
		model.addAttribute("cmtr", com);
		
		CommentaireForm comForm = new CommentaireForm();
		comForm.setIdCom(com.getIdCom());
		comForm.setDate(com.getDate());
		com.setUserGrimp(usr);
		com.setSiteEscalade(site);
			
		return "/editFormCommentaire";
	}
	
	@PostMapping("/commentaire/{idCom}/site/{idSiteEscalade}/edit")
	public String postEditComnt(Model model, @PathVariable("idSiteEscalade") Long idSiteEscalade, @PathVariable("idCom")Long idCom, @ModelAttribute("editFormCommentaire")CommentaireForm commentaireForm , BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Commentaire com = commentaireRepository.findById(idCom).get();
		com.setComments(commentaireForm.getComments());
		com.setDate(date);
		com.setSiteEscalade(site);
		com.setUserGrimp(usr);
		commentaireRepository.save(com);
		
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
		
	/*============== #Suppression ======================*/
	@GetMapping("/commentaire/{idCom}/site/{idSiteEscalade}/delete")
	public String deleteComnt(@PathVariable("idCom")Long idCom,@PathVariable("idSiteEscalade") Long idSiteEscalade, Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		commentaireRepository.deleteById(idCom);
		
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
}
