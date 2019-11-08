package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.oc.dao.UserGrimpRepository;
import com.oc.entities.UserGrimp;
import com.oc.forms.UserGrimpForm;

@Service
public class UserGrimpService {

	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	public void saveUserGrimpForm(UserGrimpForm userGrimpForm, BindingResult result) {

		if (userGrimpForm.getUsername().isEmpty() ) {
			result.addError(new FieldError("form", "username", "TG!"));
		} else {
			UserGrimp newUserGrimp = new UserGrimp();
			newUserGrimp.setPseudo(userGrimpForm.getUsername());
			newUserGrimp.setEmail(userGrimpForm.getEmail());
			newUserGrimp.setPassword(userGrimpForm.getPassword());
			
			userGrimpRepository.save(newUserGrimp);	
		}
		
	}

}
