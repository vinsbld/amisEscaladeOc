package com.oc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oc.entities.RoleEnum;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final String adminRole = RoleEnum.ADMINISTRATOR.name();
	
	private final UserDetailsService userDetailsService;
	
	/*
	 * constructeur de la classe SecurityConfig, injection du service
	 * UserDetailService
	 */
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				/*
				 * .antMatchers("/inscription", "/lib/**", "/js/**", "/img/**",
				 * "/css/**","/index", "/site_escalade", "/connexion",
				 * "/le_site_escalade/{idSiteEscalade}/view").permitAll() .and()
				 * .authorizeRequests() .anyRequest().authenticated()
				 */
			.antMatchers("/topo").authenticated()
			.and()
			.authorizeRequests()
			.anyRequest().permitAll()
			.and()
				.formLogin().loginPage("/connexion").defaultSuccessUrl("/index").failureUrl("/connexion")
				.usernameParameter("username").passwordParameter("password")
			.and()
				.logout().invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/connexion")
			.and()
				.csrf()
			.and()
				.sessionManagement().maximumSessions(1).expiredUrl("/connexion");


	}
	
	//chiffrement de mot de passe
	@Bean
	 public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    
	/*
	 * précise les composants à utiliser pour authentifier les utilisateurs 
	 * le service UserDetailsService et le type d’encodage
	 */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
	

