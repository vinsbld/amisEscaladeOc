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
	
	public void saveSiteEscalade(SiteEscaladeForm siteEscaladeForm, BindingResult result) {
		
		SiteEscalade newSiteEscalade = new SiteEscalade();
		newSiteEscalade.setNomSiteEscalade(siteEscaladeForm.getName());
		newSiteEscalade.setDepartement(siteEscaladeForm.getDepartement());
		newSiteEscalade.setVille(siteEscaladeForm.getVille());
		
		siteEscaladeRepository.save(newSiteEscalade);
	}

}
