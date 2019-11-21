package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.SecteurRepository;
import com.oc.entities.Secteur;
import com.oc.forms.SecteurForm;

@Service
public class SecteurService {
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	public void saveSecteur(SecteurForm secteurForm, BindingResult result) {
		
		Secteur newSecteur = new Secteur();
		newSecteur.setNomDuSecteur(secteurForm.getNomDuSecteur());
		newSecteur.setLocalisation(secteurForm.getLocalisation());
		newSecteur.setAcces(secteurForm.getAcces());
		
		secteurRepository.save(newSecteur);
		
	}

}
