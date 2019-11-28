package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.LongueurRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Longueur;
import com.oc.entities.Voie;
import com.oc.forms.LongueurForm;

@Service
public class LongueurService {
	
	@Autowired
	private LongueurRepository longueurRepository;
	
	@Autowired
	private VoieRepository voieRepository;
	
	public void saveLongueur(long idVoie, LongueurForm longueurForm, BindingResult result) {
		
		Longueur newLongueur = new Longueur();
		newLongueur.setDistance(longueurForm.getDistance());
		newLongueur.setHauteur(longueurForm.getHauteur());
		
		Voie voie = voieRepository.findById(idVoie).get();
		
		newLongueur.setVoie(voie);
		longueurRepository.save(newLongueur);
		
	}
	
	public void modifyLongueur(long idLongueur, LongueurForm longueurForm, BindingResult result) {
		
		Longueur longr = longueurRepository.findById(idLongueur).get();
		longr.setDistance(longueurForm.getDistance());
		longr.setHauteur(longueurForm.getHauteur());
		
		longueurRepository.save(longr);
		
	}

}
