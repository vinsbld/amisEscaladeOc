package com.oc.metier;

import java.util.List;

import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Voie;

public interface IMetier {
	
	/* ================== #Sites escalades ================== */
	// liste des sites d'esclades
	public List<SiteEscalade> getAllSites();
	
	// liste des sites appartenants a un utilisateur
	public List<SiteEscalade> findByUserGrimp(Long idUserGrimp);
	
	/* ================== #Secteurs ================== */
	// liste des secteurs
	public List<Secteur> getAllSecteurs();
	
	// liste des secteurs appartenants a un site d'escalade
	public List<Secteur> findBySite(long idSite);

	/* ================== #Voies ================== */
	// liste des voies
	public List<Voie> getAllVoies();
	
	// liste des voies appartenants un un secteur
	public List<Voie> findBySecteur(long idVoie);

}
