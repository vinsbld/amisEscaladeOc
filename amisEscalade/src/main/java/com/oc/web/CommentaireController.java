package com.oc.web;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	final static Logger logger = LogManager.getLogger(Level.ALL);
	
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
	 * Comnt.display the comments by site 
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @return the commentaire_site
	 */
	@GetMapping("/site/{idSiteEscalade}/commentaire")
	public String comnt(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade) {	
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Commentaire> cmtr = commentaireRepository.findComBySite(site.getIdSiteEscalade());
		model.addAttribute("cmtr", cmtr);
		
		CommentaireForm cmts = new CommentaireForm();
		model.addAttribute("commentaireForm", cmts);
		
		logger.info("un utilisateur consulte les commentaires du site d'escalade "+ site.getNomSiteEscalade() +" id du site n°"+site.getIdSiteEscalade());
					
		return "/commentaire_site";
	}
	
	/*============== #Création ======================*/
	/**
	 * Comnt R.save a comment
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param commentaireForm the commentaire form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the commentaire_site
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
			logger.error("l'utilisateur"+usr.getPseudo()+ " n'a pas saisi de carctères");
			result.rejectValue("comments", "commentsBlank.value", "votre commentaire ne doit pas être vide !");
			model.addAttribute("commentaireForm", commentaireForm);
			return "/commentaire_site";
		}
		else if(commentaireForm.getComments().length()>255) {
			logger.error("l'utilisateur "+ usr.getPseudo() + " à saisi un nombre de caractères supèrieur à 255");
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
		logger.info("l'utilisateur "+usr.getPseudo()+" a laissé un commentaire pour le site "+site.getNomSiteEscalade()+ "l'id du commentaire est n°"+newCommentaire.getIdCom());
		}
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
		
	}

	/*============== #Modification ======================*/
	/**
	 * Edits the comnt.modify user's comments
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idCom the id com
	 * @param commentaireForm the commentaire form
	 * @return the editFormCommentaire
	 */
	@GetMapping("/commentaire/{idCom}/site/{idSiteEscalade}/edit")
	public String editComnt(Model model, @PathVariable("idSiteEscalade") Long idSiteEscalade, @PathVariable("idCom")Long idCom, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);

		Commentaire com = commentaireRepository.findById(idCom).get();
		model.addAttribute("commentaireForm", com);
		
		CommentaireForm comForm = new CommentaireForm();
		comForm.setIdCom(com.getIdCom());
		comForm.setDate(com.getDate());
		com.setUserGrimp(usr);
		com.setSiteEscalade(site);
			
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un fomulaire de modification pour le commentaire n°"+com.getIdCom());
		
		return "/editFormCommentaire";
	}
	
	/**
	 * Post edit comnt. save the user's comments modifications
	 *
	 * @param model the model
	 * @param idSiteEscalade the id site escalade
	 * @param idCom the id com
	 * @param commentaireForm the commentaire form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the commentaire
	 */
	@PostMapping("/commentaire/{idCom}/site/{idSiteEscalade}/edit")
	public String postEditComnt(Model model, @PathVariable("idSiteEscalade") Long idSiteEscalade, @PathVariable("idCom")Long idCom, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Commentaire com = commentaireRepository.findById(idCom).get();
		model.addAttribute("commentaireForm", com);
		
		if (result.hasErrors()) {
			model.addAttribute("commentaireForm", commentaireForm);
			return"/editFormCommentaire";
		}
		else if (commentaireForm.getComments().isBlank()) {
			result.rejectValue("comments", "commentsBlank.value", "votre commentaire ne doit pas être vide !");
			model.addAttribute("commentaireForm", commentaireForm);
			logger.error("lutilisteur "+usr.getPseudo()+" n'a pas saisi de caractères");
			return"/editFormCommentaire";
		}
		else if (commentaireForm.getComments().length()>255) {
			result.rejectValue("comments", "commentsLength.value", "votre commentaire ne doit pas dépasser 255 caractères ;( ");
			model.addAttribute("commentaireForm", commentaireForm);
			logger.error("lutilisteur "+usr.getPseudo()+" a saisi plus de 255 caractères");
			return"/editFormCommentaire";
		}
		else {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		com.setComments(commentaireForm.getComments());
		com.setDate(date);
		com.setSiteEscalade(site);
		com.setUserGrimp(usr);
		commentaireRepository.save(com);
		logger.info("lutilisteur "+usr.getPseudo()+" a enregistré les modifications apportées pour le commentaire n°"+com.getIdCom());
		}
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
		
	/*============== #Suppression ======================*/
	/**
	 * Delete comnt.delete a comment
	 *
	 * @param idCom the id com
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @return the commentaire
	 */
	@GetMapping("/commentaire/{idCom}/site/{idSiteEscalade}/delete")
	public String deleteComnt(@PathVariable("idCom")Long idCom,@PathVariable("idSiteEscalade") Long idSiteEscalade, Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		logger.warn("l'utilisateur "+usr.getPseudo()+" a supprimé le message n°"+idCom);
		
		commentaireRepository.deleteById(idCom);	
		
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
}
