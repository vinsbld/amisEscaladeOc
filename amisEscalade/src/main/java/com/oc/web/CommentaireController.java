package com.oc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.SiteEscalade;

@Controller
public class CommentaireController {
	
	// injections repositories
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	// get and post Mapping
	/*============== #Pages ======================*/
	@GetMapping("/site/{idSiteEscalade}/commentaire")
	public String comnt(Model model, @PathVariable("idSiteEscalade")Long idSiteEscalade) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		return "commentaire_site";
	}
	
	
	/*============== #Création ======================*/
	/*============== #Modification ======================*/
	/*============== #Suppression ======================*/

}
