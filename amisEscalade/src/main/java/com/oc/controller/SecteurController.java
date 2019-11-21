package com.oc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.SecteurRepository;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.forms.SecteurForm;
import com.oc.metier.SecteurService;

@Controller
public class SecteurController {
	
	@Autowired
	private SecteurService secteurService;
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	@GetMapping("/secteur/create")
	public String formSect() {
		return"formSecteur";
	}
			
	@PostMapping("/secteur/create")
	public String ajouterSecteur(Model model, @ModelAttribute("secteurForm") SecteurForm secteurForm,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "formSecteur";
		}
		
		secteurService.saveSecteurForm(secteurForm, result);
		
		return "addSecteurToSiteEscalade";
	}
		
	
	  @GetMapping("/addSecteurToSiteEscalade") public String secteurSiteEscal(Model model,
	  
	  @RequestParam(name="page", defaultValue="0")int p,
	  
	  @RequestParam(name="size", defaultValue="4")int s,
	  
	  @RequestParam(name="motCle", defaultValue="")String motCle) { Page<Secteur>
	  pageSecteurs = secteurRepository.chercher("%"+motCle+"%", new PageRequest(p,s));
	  
	  model.addAttribute("listSecteur", pageSecteurs.getContent()); int[]pages =
	  new int[pageSecteurs.getTotalPages()]; model.addAttribute("pages", pages);
	  model.addAttribute("size", s); model.addAttribute("pageCourante", p);
	  model.addAttribute("motCle", motCle); return "addSecteurToSiteEscalade"; }
	 
	
}
