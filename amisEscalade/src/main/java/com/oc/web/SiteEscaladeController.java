package com.oc.web;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.CodexRepository;
import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.Codex;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.UserGrimp;
import com.oc.forms.SiteEscaladeForm;

/**
 * The Class SiteEscaladeController.
 */
@Controller
public class SiteEscaladeController {
	
	final static Logger logger = LogManager.getLogger(Level.ALL);
	
	//injections repositories
	/** The site escalade repository. */
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	/** The secteur repository. */
	@Autowired
	private SecteurRepository secteurRepository;
	
	/** The codex repository. */
	@Autowired
	private CodexRepository codexRepository;
	
	
	// get and post Mapping
	/*============== #Pages ======================*/
	/**
	 * Site escal.display site escalade
	 *
	 * @param model the model
	 * @return the site escalade page
	 */
	@GetMapping("/site_escalade")
	public String siteEscal(Model model) {
				
		Iterable<SiteEscalade> listeSite = siteEscaladeRepository.findAll();
		model.addAttribute("listSite", listeSite);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);

		logger.info("Un utilisateur consulte la liste des site d'escalade");
		
		return "site_escalade";
	}
	
	/**
	 * Site result.display the result of researchs
	 *
	 * @param model the model
	 * @param mc the mc (mot clé)
	 * @return the site escalade page
	 */
	@GetMapping("/site_escalade/result")
	public String siteResult(Model model, @RequestParam(name = "mc", defaultValue = "")String mc) {
		
		if (mc.isEmpty()) {
			Iterable<SiteEscalade> liste = siteEscaladeRepository.findAll();
			model.addAttribute("listSite", liste);
		}else {
			List<SiteEscalade> listeSite = siteEscaladeRepository.chercher("%"+mc+"%");
			model.addAttribute("mc", mc);
			model.addAttribute("listSite", listeSite);
			logger.info("Un utilisateur effectue une recherche avec comme mot clé : "+mc);
		}
		
		
		return "site_escalade";
	}
	
	/**
	 * Le site.display the site's details informations
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @return the le site escalade page
	 */
	@GetMapping("/le_site_escalade/{idSiteEscalade}/view")
	public String leSite(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		Iterable<Secteur> sec = secteurRepository.findBySite(idSiteEscalade);
		model.addAttribute("sec", sec);
		
		logger.info("Un utilisateur consulte le site d'escalade "+site.getNomSiteEscalade());
		
		return"le_site_escalade";
	}
	
	/*============== #Création ======================*/
	/**
	 * Form sit.display the site form
	 *
	 * @param model the model
	 * @return the site form
	 */
	@GetMapping("/site_escalade/create")
	public String formSit(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);

		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		SiteEscaladeForm siteForm = new SiteEscaladeForm();
		model.addAttribute("siteEscaladeForm", siteForm);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un formulaire de création de site d'escalade");
	
		return"formSiteEscalade";
	}

	/**
	 * Ajouter site escalade.save a new site 
	 *
	 * @param model the model
	 * @param siteEscaladeForm the site escalade form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the site_escalade page
	 */
	@PostMapping("/site_escalade/create")
	public String ajouterSiteEscalade(Model model, @ModelAttribute("siteEscaladeForm") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		if(result.hasErrors()) {
			model.addAttribute("siteEscaladeForm", siteEscaladeForm);
			return "formSiteEscalade";
		}
		else if (siteEscaladeForm.getSiteName().isBlank()) {
			result.rejectValue("siteName", "siteName.value", "le nom ne doit pas être vide !");
			model.addAttribute("siteEscaladeForm", siteEscaladeForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de site vide ");
			return "formSiteEscalade";
		}
		else if (siteEscaladeForm.getSiteName().length()>25) {
			result.rejectValue("siteName", "siteName.value", "le nom ne doit pas dépasser 25 caractères !");
			model.addAttribute("siteEscaladeForm", siteEscaladeForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de site de plus de 25 caractères");
			return "formSiteEscalade";
		}
		else {
			SiteEscalade newSiteEscalade = new SiteEscalade();
			newSiteEscalade.setIdSiteEscalade(siteEscaladeForm.getIdSiteEscalade());
			newSiteEscalade.setNomSiteEscalade(siteEscaladeForm.getSiteName()); 
			newSiteEscalade.setLieu(siteEscaladeForm.getLieu());			
			newSiteEscalade.setOfficiel(siteEscaladeForm.isOfficiel());
			newSiteEscalade.setUserGrimp(usr);
			siteEscaladeRepository.save(newSiteEscalade);
			logger.info("l'utilisateur "+usr.getPseudo()+" a créer un nouveau site d'escalade "+newSiteEscalade.getNomSiteEscalade()+" l'id du site est "+newSiteEscalade.getIdSiteEscalade());
		}

		return "redirect:/site_escalade";
	}
	
	/*============== #Modification ======================*/
	/**
	 * Edits the site.add a site's modifications
	 *
	 * @param siteEscaladeForm the site escalade form
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @return the edit site escalade form
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/edit")
	public String editSite(@ModelAttribute("siteForm") SiteEscaladeForm siteEscaladeForm, @PathVariable("idSiteEscalade") long idSiteEscalade, Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		SiteEscaladeForm siteForm = new SiteEscaladeForm();
		siteForm.setIdSiteEscalade(site.getIdSiteEscalade());
		siteForm.setSiteName(site.getNomSiteEscalade());
		siteForm.setLieu(site.getLieu());
		siteForm.setOfficiel(site.isOfficiel());
		model.addAttribute("siteForm", siteForm);
		
		logger.info("l'utilisateur "+usr.getPseudo()+" a demandé un formulaire de modifications de sites d'escalades pour le site n°"+site.getIdSiteEscalade());
		 
		return "editFormSiteEscalade";
	}
	
	/**
	 * Update site escalade.save the site's modifications
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @param siteEscaladeForm the site escalade form
	 * @param result the result
	 * @param redirectAttributes the redirect attributes
	 * @return the site escalade page
	 */
	@PostMapping("/site_escalade/{idSiteEscalade}/update")
	public String updateSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, @ModelAttribute("siteForm") SiteEscaladeForm siteEscaladeForm, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		Iterable<Codex> cdxList = codexRepository.findAllCity();
		model.addAttribute("cdxList", cdxList);
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		model.addAttribute("site", site);
		
		if (result.hasErrors()) {
			return "editFormSiteEscalade";
		}
		else if (siteEscaladeForm.getSiteName().isBlank()) {
			result.rejectValue("siteName", "siteName.value", "le nom ne doit pas être vide !");
			model.addAttribute("siteForm", siteEscaladeForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de site vide ");
			return "editFormSiteEscalade";
		}
		else if (siteEscaladeForm.getSiteName().length()>25) {
			result.rejectValue("siteName", "siteName.value", "le nom ne doit pas dépasser 25 caractères !");
			model.addAttribute("siteForm", siteEscaladeForm);
			logger.error("l'utilisateur "+usr.getPseudo()+" a saisi un nom de site de plus de 25 caractères");
			return "editFormSiteEscalade";
		}
		
		else {
		site.setNomSiteEscalade(siteEscaladeForm.getSiteName());
		site.setLieu(siteEscaladeForm.getLieu());
		site.setOfficiel(siteEscaladeForm.isOfficiel());
		siteEscaladeRepository.save(site);
		logger.info("l'utilisateur "+usr.getPseudo()+" a enregistré les modifications apportées au site d'escalade n°"+site.getIdSiteEscalade());
		}
		return "redirect:/le_site_escalade/"+idSiteEscalade+"/view";
	}
	
	/*============== #Suppression ======================*/
	/**
	 * Delete site escalade.
	 *
	 * @param idSiteEscalade the id site escalade
	 * @param model the model
	 * @param redirectAttributes the redirect attributes
	 * @return the site_escalade page
	 */
	@GetMapping("/site_escalade/{idSiteEscalade}/delete")
	public String deleteSiteEscalade(@PathVariable ("idSiteEscalade") long idSiteEscalade, Model model, final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		logger.warn("l'utilisateur "+usr.getPseudo()+" a supprimé le site d'escalade n°"+idSiteEscalade);
		
		siteEscaladeRepository.deleteById(idSiteEscalade);
		
		return "redirect:/site_escalade";
				
	}
	
}
