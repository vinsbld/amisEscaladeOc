package com.oc.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oc.dao.UserGrimpRepository;
import com.oc.entities.UserGrimp;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		UserGrimp userGrimp = userGrimpRepository.findByPseudo(username);
		
		if (userGrimp == null) {
            throw new UsernameNotFoundException("Utilisateur inconnu : " + username);
        }
		else {
			return userGrimp;
		}
		
	}


	
	
}
