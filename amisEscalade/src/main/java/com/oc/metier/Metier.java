package com.oc.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.oc.dao.LongueurRepository;
import com.oc.dao.SecteurRepository;
import com.oc.dao.SiteEscaladeRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Secteur;
import com.oc.entities.SiteEscalade;
import com.oc.entities.Voie;

@Service("métier")
public class Metier implements IMetier {
	
	// injections repositories
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;
	@Autowired
	private SecteurRepository secteurRepository;
	@Autowired
	private VoieRepository voieRepository;
	@Autowired
	private LongueurRepository longueurRepository;
	
	// interface implémentation
	/* ================== #Sites escalades ================== */
	@Override
	public List<SiteEscalade> getAllSites() {
		return Lists.newArrayList(siteEscaladeRepository.findAll());
	}

	@Override
	public List<SiteEscalade> findByUserGrimp(Long idUserGrimp) {
		return Lists.newArrayList(siteEscaladeRepository.findByUserGrimp(idUserGrimp));
	}
	
	/* ================== #Secteurs ================== */
	@Override
	public List<Secteur> getAllSecteurs(){
		return Lists.newArrayList(secteurRepository.findAll());
	}

	@Override
	public List<Secteur> findBySite(long idSite) {
		return Lists.newArrayList(secteurRepository.findBySite(idSite));
	}
	
	/* ================== #Voies ================== */
	@Override
	public List<Voie> getAllVoies(){
		return Lists.newArrayList(voieRepository.findAll());
	}
	
	@Override
	public List<Voie> findBySecteur(long idVoie){
		return Lists.newArrayList(voieRepository.findBySecteur(idVoie));
	}
	/* ================== #Longueurs ================== */

	@Override
	public List<Longueur> getAllLongueurs() {
		return Lists.newArrayList(longueurRepository.findAll());
	}

	@Override
	public List<Longueur> findByVoie(long idVoie) {
		return Lists.newArrayList(longueurRepository.findByVoie(idVoie));
	}
	
}
