package com.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * The Class BCryptManagerUtil.
 */
//cette méthode permet de hasher le mot de passe utilisateur
@Component
public class BCryptManagerUtil {
	
	/** The password encoder. */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * Instantiates a new b crypt manager util.
	 *
	 * @param passwordEncoder the password encoder
	 */
	@Autowired
	public BCryptManagerUtil(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Passwordencoder.
	 *
	 * @return the password encoder
	 */
	@Bean(name="passwordEncoder")
	public static PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
