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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
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
		
		secteurService.saveSecteur(secteurForm, result);
		
		return "secteur";
	}
		
	
	  @GetMapping("/secteur") public String secteurSiteEscal(Model model,
	  @RequestParam(name="page", defaultValue="0")int p,	  
	  @RequestParam(name="size", defaultValue="4")int s,	  
	  @RequestParam(name="motCle", defaultValue="")String motCle) { Page<Secteur>
	  pageSecteurs = secteurRepository.chercher("%"+motCle+"%", new PageRequest(p,s));
	  
	  model.addAttribute("listSecteur", pageSecteurs.getContent()); int[]pages =
	  new int[pageSecteurs.getTotalPages()]; model.addAttribute("pages", pages);
	  model.addAttribute("size", s); model.addAttribute("pageCourante", p);
	  model.addAttribute("motCle", motCle); 
	  return "secteur"; 
	  }
	 
	  @GetMapping("/secteur/{idSecteur}/edit")
	  public String editSecteur(@PathVariable("idSecteur") long idSecteur, Model model) {
		  Optional<Secteur> sec = secteurRepository.findById(idSecteur);
		  
		  Secteur addSecteur = null;
		  
		  if(sec.isPresent()) {
				addSecteur= sec.get();
			}
			model.addAttribute("addSecteur", addSecteur);
			
			return"editFormSecteur";
		}
	  
	  @PostMapping("/secteur/{idSecteur}/update")
	  public String updateSecteur(@PathVariable("idSecteur") long idSecteur, Model model, @ModelAttribute("editFormSecteur") SecteurForm secteurForm, BindingResult result, final RedirectAttributes redirectAttributes){
		secteurService.saveSecteur(secteurForm, result);  
		return"redirect:/secteur";
	  }
	  
	  @GetMapping("/secteur/{idSecteur}/delete")
	  public String deleteSecteur(@PathVariable("idSecteur") long idSecteur, Model model, final RedirectAttributes redirectAttributes) {
		  
		  secteurRepository.deleteById(idSecteur);
		  
		  return "redirect:/secteur";
	  }
	  
		  
}
	  
