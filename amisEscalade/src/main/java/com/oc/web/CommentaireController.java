package com.oc.web;

import java.util.Calendar;
import java.util.Date;

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

/**
 * The Class CommentaireController.
 */
@Controller
public class CommentaireController {
	
	// injections repositories
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	/** The commentaire repository. */
	@Autowired
	private CommentaireRepository commentaireRepository;
		
	// get and post Mapping
	/*============== #Pages ======================*/
	/**
	 * Comnt.
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @return the string
	 */
	@GetMapping("/site/{idSiteEscalade}/commentaire")
	public String comnt(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Commentaire> cmtr = commentaireRepository.findComBySite(site.getIdSiteEscalade());
		model.addAttribute("cmtr", cmtr);
		
		CommentaireForm cmts = new CommentaireForm();
		model.addAttribute("commentaireForm", cmts);
					
		return "/commentaire_site";
	}
	
	/*============== #Création ======================*/
	/**
	 * Comnt R.
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param commentaireForm the commentaire form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@PostMapping("/commentaire/site/{idSiteEscalade}")
	public String comntR(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm, 
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Commentaire> cmtr = commentaireRepository.findComBySite(site.getIdSiteEscalade());
		model.addAttribute("cmtr", cmtr);
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
			
		if(result.hasErrors()) {
			model.addAttribute("commentaireForm", commentaireForm);
			return "/commentaire_site";
		}
		else if(commentaireForm.getComments().isBlank()) {
			result.rejectValue("comments", "commentsBlank.value", "votre commentaire ne doit pas être vide !");
			model.addAttribute("commentaireForm", commentaireForm);
			return "/commentaire_site";
		}
		else if(commentaireForm.getComments().length()>255) {
			result.rejectValue("comments", "commentsLength.value", "votre commentaire ne doit pas dépasser 255 caractères ;( ");
			model.addAttribute("commentaireForm", commentaireForm);
			return "/commentaire_site";
		}
		else {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Commentaire newCommentaire = new Commentaire();
		newCommentaire.setSiteEscalade(site);
		newCommentaire.setUserGrimp(usr);
		newCommentaire.setComments(commentaireForm.getComments());
		newCommentaire.setDate(date);
		commentaireRepository.save(newCommentaire);
		}
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
		
	}

	/*============== #Modification ======================*/
	/**
	 * Edits the comnt.
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idCom the id com
	 * @param commentaireForm the commentaire form
	 * @return the string
	 */
	@GetMapping("/commentaire/{idCom}/site/{idSiteEscalade}/edit")
	public String editComnt(Model model, @PathVariable("idSiteEscalade") Long idSiteEscalade, @PathVariable("idCom")Long idCom, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm) {
		
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
	
	/**
	 * Post edit comnt.
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idCom the id com
	 * @param commentaireForm the commentaire form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the string
	 */
	@PostMapping("/commentaire/{idCom}/site/{idSiteEscalade}/edit")
	public String postEditComnt(Model model, @PathVariable("idSiteEscalade") Long idSiteEscalade, @PathVariable("idCom")Long idCom, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm , BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Commentaire com = commentaireRepository.findById(idCom).get();
		model.addAttribute("cmtr", com);
		
		if (result.hasErrors()) {
			model.addAttribute("commentaireForm", commentaireForm);
			return"/editFormCommentaire";
		}
		else if (commentaireForm.getComments().isBlank()) {
			result.rejectValue("comments", "commentsBlank.value", "votre commentaire ne doit pas être vide !");
			model.addAttribute("commentaireForm", commentaireForm);
			return"/editFormCommentaire";
		}
		else if (commentaireForm.getComments().length()>255) {
			result.rejectValue("comments", "commentsLength.value", "votre commentaire ne doit pas dépasser 255 caractères ;( ");
			model.addAttribute("commentaireForm", commentaireForm);
			return"/editFormCommentaire";
		}
		else {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		com.setComments(commentaireForm.getComments());
		com.setDate(date);
		com.setSiteEscalade(site);
		com.setUserGrimp(usr);
		commentaireRepository.save(com);
		}
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
		
	/*============== #Suppression ======================*/
	/**
	 * Delete comnt.
	 *
	 * @param idCom the id com
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/commentaire/{idCom}/site/{idSiteEscalade}/delete")
	public String deleteComnt(@PathVariable("idCom")Long idCom,@PathVariable("idSiteEscalade") Long idSiteEscalade, Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		commentaireRepository.deleteById(idCom);
		
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
}
