package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.SecteurRepository;
import com.oc.dao.VoieRepository;
import com.oc.entities.Secteur;
import com.oc.entities.Voie;
import com.oc.forms.UserGrimpForm;
import com.oc.forms.VoieForm;

@Service
public class VoieService {
	
	@Autowired
	private VoieRepository voieRepository;
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	public void saveVoie(long idSecteur, VoieForm voieForm, BindingResult result) {
		
		Voie newVoie = new Voie();
		newVoie.setNomDeVoie(voieForm.getName());
		newVoie.setCotation(voieForm.getCotation());
		
		Secteur voieSec = secteurRepository.findById(idSecteur).get();
		
		newVoie.setSecteur(voieSec);
		voieRepository.save(newVoie);
		
	}
	
	public void modifyVoie(long idVoie, VoieForm voieForm, BindingResult result) {
		
		Voie voi = voieRepository.findById(idVoie).get();
		voi.setNomDeVoie(voieForm.getName());
		voi.setCotation(voieForm.getCotation());
		
		voieRepository.save(voi);
	}

}
