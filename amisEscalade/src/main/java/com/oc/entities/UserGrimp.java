package com.oc.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.oc.security.BCryptManagerUtil;

/**
 * The Class UserGrimp.
 */
@Entity
public class UserGrimp implements UserDetails {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id user grimp. */
	@Id @GeneratedValue
	private long idUserGrimp;
	
	// attributs d un utilisateur
	/** The pseudo. */
	@NotEmpty(message = "votre pseudo doit contenir minimum 2 caractères et maximum 30")
	@Size(min = 2, max = 30)
	@Column(unique = true)
	private String pseudo;
	
	/** The email. */
	@NonNull()
	@Column(unique = true)
	@Email(message = "merci de saisir une adresse mail correcte")
	private String email;
	
	/** The password. */
	@Size(min = 4)
	@NotEmpty(message = "votre mot de passe doit contenir minimum 4 caratères")
	private String password;
	
	/** The site escalades. */
	// un utilisateur a une collection de sites
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
	private Collection<SiteEscalade> siteEscalades;
	
	/** The topos. */
	// un utilisateur a une collection de topos
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	private Collection<Topo> topos;
	 
	/** The reservations. */
	// un utilisateur a une collection de reservations
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
	private Collection<Reservation> reservations;
	
	/** The commentaires. */
	// un utilisateur a une collection de commentaires
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Commentaire>commentaires;

	/** The roles. */
	// collection de roles
	@ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private List<RoleEnum> roles;
	
	/**
	 * Instantiates a new user grimp.
	 */
	// constructeur par defaut
	public UserGrimp () {
		this.roles = Collections.singletonList(RoleEnum.USER);
	}

	/**
	 * Instantiates a new user grimp.
	 *
	 * @param idUserGrimp the id user grimp
	 * @param pseudo the pseudo
	 * @param email the email
	 * @param password the password
	 * @param siteEscalades the site escalades
	 * @param topos the topos
	 * @param reservations the reservations
	 * @param commentaires the commentaires
	 * @param roles the roles
	 */
	// constructeur avec parametres
	public UserGrimp(long idUserGrimp,
			@NotEmpty(message = "votre pseudo doit contenir minimum 2 caractères et maximum 30") @Size(min = 2, max = 30) String pseudo,
			@Email(message = "merci de saisir une adresse mail correcte") String email,
			@Size(min = 4) @NotEmpty(message = "votre mot de passe doit contenir minimum 4 caratères") String password,
			Collection<SiteEscalade> siteEscalades, Collection<Topo> topos, Collection<Reservation> reservations,
			Collection<Commentaire> commentaires, List<RoleEnum> roles) {
		super();
		this.idUserGrimp = idUserGrimp;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.siteEscalades = siteEscalades;
		this.topos = topos;
		this.reservations = reservations;
		this.commentaires = commentaires;
		this.roles = roles;
	}

	/**
	 * Gets the authorities.
	 *
	 * @return the authorities
	 */
	//getAuthorities() permet de recuperer les oôles de l utilisateur dans un format que Spring Security peut interpreter
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		String roles = org.springframework.util.StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }

	/**
	 * Gets the id user grimp.
	 *
	 * @return the id user grimp
	 */
	// getters and setters
	public long getIdUserGrimp() {
		return idUserGrimp;
	}

	/**
	 * Sets the id user grimp.
	 *
	 * @param idUserGrimp the new id user grimp
	 */
	public void setIdUserGrimp(long idUserGrimp) {
		this.idUserGrimp = idUserGrimp;
	}

	/**
	 * Gets the pseudo.
	 *
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Sets the pseudo.
	 *
	 * @param pseudo the new pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the site escalades.
	 *
	 * @return the site escalades
	 */
	public Collection<SiteEscalade> getSiteEscalades() {
		return siteEscalades;
	}

	/**
	 * Sets the site escalades.
	 *
	 * @param siteEscalades the new site escalades
	 */
	public void setSiteEscalades(Collection<SiteEscalade> siteEscalades) {
		this.siteEscalades = siteEscalades;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<RoleEnum> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<RoleEnum> roles) {
		this.roles = roles;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the topos.
	 *
	 * @return the topos
	 */
	public Collection<Topo> getTopos() {
		return topos;
	}

	/**
	 * Sets the topos.
	 *
	 * @param topos the new topos
	 */
	public void setTopos(Collection<Topo> topos) {
		this.topos = topos;
	}

	/**
	 * Gets the reservations.
	 *
	 * @return the reservations
	 */
	public Collection<Reservation> getReservations() {
		return reservations;
	}
	
	/**
	 * Sets the reservations.
	 *
	 * @param reservations the new reservations
	 */
	public void setReservations(Collection<Reservation> reservations) {
	this.reservations = reservations;	
	}

	/**
	 * Gets the commentaires.
	 *
	 * @return the commentaires
	 */
	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}

	/**
	 * Sets the commentaires.
	 *
	 * @param commentaires the new commentaires
	 */
	public void setCommentaires(Collection<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	@Override
	public String getUsername() {
		
		return pseudo;
	}

	/**
	 * Checks if is account non expired.
	 *
	 * @return true, if is account non expired
	 */
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	/**
	 * Checks if is account non locked.
	 *
	 * @return true, if is account non locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	/**
	 * Checks if is credentials non expired.
	 *
	 * @return true, if is credentials non expired
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}