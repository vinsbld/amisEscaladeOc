package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.VoieRepository;
import com.oc.entities.Voie;
import com.oc.forms.UserGrimpForm;
import com.oc.forms.VoieForm;

@Service
public class VoieService {
	
	@Autowired
	private VoieRepository voieRepository;
	
	public void saveVoieForm(VoieForm voieForm, BindingResult result) {
		
		Voie newVoie = new Voie();
		newVoie.setNomDeVoie(voieForm.getName());
		newVoie.setCotation(voieForm.getCotation());
		
		voieRepository.save(newVoie);
		
	}
	

}
