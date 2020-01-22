package com.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oc.dao.UserGrimpRepository;
import com.oc.entities.UserGrimp;


/**
 * The Class UserGrimpSecuService.
 */
@Service
public class UserGrimpSecuService implements UserDetailsService{
	
/** The user grimp repository. */
private final UserGrimpRepository userGrimpRepository;

/**
 * Instantiates a new user grimp secu service.
 *
 * @param userGrimpRepository the user grimp repository
 */
@Autowired
public UserGrimpSecuService(UserGrimpRepository userGrimpRepository) {
    this.userGrimpRepository = userGrimpRepository;
}

/**
 * Load user by username.
 *
 * @param pseudo the pseudo
 * @return the user details
 * @throws UsernameNotFoundException the username not found exception
 */
@Override
public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
    UserGrimp userGrimp = userGrimpRepository.findByPseudo(pseudo.toLowerCase());
    if (userGrimp == null) {
        throw new UsernameNotFoundException("No user present with username : " + userGrimp);
    }
    else {
        return userGrimp;
    }
}

}
