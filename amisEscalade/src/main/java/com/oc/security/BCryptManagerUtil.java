package com.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//cette méthode permet de hasher le mot de passe utilisateur
@Component
public class BCryptManagerUtil {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public BCryptManagerUtil(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean(name="passwordEncoder")
	public static PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
