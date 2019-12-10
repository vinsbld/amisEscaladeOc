package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oc.dao.UserGrimpRepository;
import com.oc.entities.UserGrimp;
import com.oc.forms.UserGrimpForm;

@Service
public class UserGrimpService {

	@Autowired
	private UserGrimpRepository userGrimpRepository;

	public void saveUserGrimpForm(UserGrimpForm userGrimpForm) {

		UserGrimp newUserGrimp = new UserGrimp();
		newUserGrimp.setPseudo(userGrimpForm.getUsername());
		newUserGrimp.setEmail(userGrimpForm.getEmail());
		newUserGrimp.setPassword(userGrimpForm.getPassword());

		userGrimpRepository.save(newUserGrimp);
	}
	

}
