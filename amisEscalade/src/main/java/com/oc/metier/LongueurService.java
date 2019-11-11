package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.LongueurRepository;
import com.oc.entities.Longueur;
import com.oc.forms.LongueurForm;

@Service
public class LongueurService {
	
	@Autowired
	private LongueurRepository longueurRepository;
	
	public void saveLongueurForm(LongueurForm longueurForm, BindingResult result) {
		
		Longueur newLongueur = new Longueur();
		newLongueur.setDistance(longueurForm.getDistance());
		newLongueur.setHauteur(longueurForm.getHauteur());
		
		longueurRepository.save(newLongueur);
		
	}

}
