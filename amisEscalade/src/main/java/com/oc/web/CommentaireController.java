package com.oc.web;

import java.sql.PseudoColumnUsage;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.CommentaireRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.UserGrimpRepository;
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
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site/{idSiteEscalade}/commentaire")
	public String comnt(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Commentaire> cmtr = commentaireRepository.findComBySite(site.getIdSiteEscalade());
				model.addAttribute("cmtr", cmtr);
					
		UserGrimp user = new UserGrimp();
		UserGrimp usr = userGrimpRepository.findByPseudo(user.getPseudo());				
		Iterable<Commentaire> com = commentaireRepository.findComByUserGrimpName(usr.getPseudo());
		model.addAttribute("com", com);
		
		return "commentaire_site";
	}
	
	
	/*============== #Création ======================*/
	@PostMapping("/commentaire/site/{idSiteEscalade}")
	public String comntR(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade, @ModelAttribute("commentaireForm")CommentaireForm commentaireForm, final RedirectAttributes redirectAttributes) {

		Commentaire newCommentaire = new Commentaire();
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		newCommentaire.setSiteEscalade(site);
		newCommentaire.setUserGrimp(usr);
		newCommentaire.setComments(commentaireForm.getComments());
		newCommentaire.setDate(new Date());
		commentaireRepository.save(newCommentaire);
		
		return"redirect:/site/"+idSiteEscalade+"/commentaire";
	}
	/*============== #Modification ======================*/
	/*============== #Suppression ======================*/

}
