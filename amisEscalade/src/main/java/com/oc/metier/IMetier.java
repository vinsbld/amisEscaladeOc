package com.oc.metier;

import java.util.List;

import com.oc.entities.SiteEscalade;

public interface IMetier {
	
	// liste des sites d'esclades
	public List<SiteEscalade> getAllSites();
	
	// liste des sites appartenants a un utilisateur
	public List<SiteEscalade> findByUserGrimp(Long idUserGrimp);

}
