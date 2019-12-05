package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.SiteEscalade;
import com.oc.forms.SiteEscaladeForm;

@Service
public class SiteEscaladeService {
	
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	
	public void saveSiteEscalade(SiteEscalade newSiteEscalade) {
		
		siteEscaladeRepository.save(newSiteEscalade);
	}
	
	public void modifySiteEscalade(long idSiteEscalade, SiteEscaladeForm siteEscaladeForm) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		site.setNomSiteEscalade(siteEscaladeForm.getName());
		site.setDepartement(siteEscaladeForm.getDepartement());
		site.setVille(siteEscaladeForm.getVille());
		
		siteEscaladeRepository.save(site);
		
	}

}
