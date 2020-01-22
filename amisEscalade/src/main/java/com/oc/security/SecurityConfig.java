package com.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oc.entities.RoleEnum;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/** The admin role. */
	private final String adminRole = RoleEnum.ADMINISTRATOR.name();
	
	/** The user details service. */
	private final UserDetailsService userDetailsService;
	
	/**
	 * Instantiates a new security config.
	 *
	 * @param userDetailsService the user details service
	 */
	/*
	 * constructeur de la classe SecurityConfig, injection du service
	 * UserDetailService
	 * @Lazy éviterait la boucle de "l'oeuf est la poule" 
	 * entre UserDetailsService et UserDetailsService 
	 */
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	
	/**
	 * Password encoder.
	 *
	 * @return the b crypt password encoder
	 */
	//chiffrement de mot de passe
	@Bean
	 public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/**
	 * Config authentication.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	/*
	 * précise les composants à utiliser pour authentifier les utilisateurs 
	 * le service UserDetailsService et le type d’encodage
	 */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	
	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/topo").authenticated()
			.and()
			.authorizeRequests()
			.anyRequest().permitAll()
			.and()
				.formLogin().loginPage("/connexion")
				.defaultSuccessUrl("/profil")
				.failureUrl("/connexion?error=true")
				.usernameParameter("username").passwordParameter("password")
			.and()
				.logout().invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/connexion?logout=true")
			.and()
				.csrf()
			.and()
				.sessionManagement().maximumSessions(1).expiredUrl("/connexion");

	}

	/**
	 * Gets the admin role.
	 *
	 * @return the admin role
	 */
	public String getAdminRole() {
		return adminRole;
	}

}
	

