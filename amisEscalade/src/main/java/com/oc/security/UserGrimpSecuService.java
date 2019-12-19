package com.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oc.dao.UserGrimpRepository;
import com.oc.entities.UserGrimp;

@Service
public class UserGrimpSecuService implements UserDetailsService{
	
private final UserGrimpRepository userGrimpRepository;

@Autowired
public UserGrimpSecuService(UserGrimpRepository userGrimpRepository) {
    this.userGrimpRepository = userGrimpRepository;
}

@Override
public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
    UserGrimp userGrimp = userGrimpRepository.findByPseudo(pseudo);
    if (userGrimp == null) {
        throw new UsernameNotFoundException("No user present with username : " + userGrimp);
    }
    else {
        return userGrimp;
    }
}

}
