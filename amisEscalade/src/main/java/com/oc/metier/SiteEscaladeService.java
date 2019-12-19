package com.oc.metier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oc.dao.SiteEscaladeRepository;
import com.oc.entities.SiteEscalade;
import com.oc.forms.SiteEscaladeForm;

@Service
public class SiteEscaladeService  {
	
	// injections repositories
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;

	
	public void modifySiteEscalade(long idSiteEscalade, SiteEscaladeForm siteEscaladeForm) {
		
		SiteEscalade site = siteEscaladeRepository.findById(idSiteEscalade).get();
		site.setNomSiteEscalade(siteEscaladeForm.getName());
		site.setDepartement(siteEscaladeForm.getDepartement());
		site.setVille(siteEscaladeForm.getVille());
		site.setOfficiel(siteEscaladeForm.isOfficiel());
		
		siteEscaladeRepository.save(site);
		
	}

}
