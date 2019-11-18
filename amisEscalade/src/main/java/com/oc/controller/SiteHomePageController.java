package com.oc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.SiteEscalade;

@Controller
public class SiteHomePageController {
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	@GetMapping("/site_escalade")
	public String siteEscal(Model model,
			@RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="size", defaultValue="5")int s,
			@RequestParam(name="motCle", defaultValue="")String motCle) {
		Page<SiteEscalade> pageSites = siteEscaladeRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		
		model.addAttribute("listSite", pageSites.getContent());
		int[]pages = new int[pageSites.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "site_escalade";
	}
	
	@GetMapping("/site_escalade/edit")
	public String editSite(Model model, long idSiteEscalade) {
		Optional<SiteEscalade> s=siteEscaladeRepository.findById(idSiteEscalade);
		SiteEscalade siteEscalade = new SiteEscalade();
		if(s.isPresent()) {
			siteEscalade = s.get();
		}
		model.addAttribute("siteEscalade", siteEscalade);
		return "editSiteEscalade";
	}
	

}
