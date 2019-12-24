package com.oc.entities;

import java.util.Collection;
import java.util.Collections;
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



@Entity
public class UserGrimp implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long idUserGrimp;
	// attributs d'un utilisateur
	@NotEmpty(message = "votre pseudo doit contenir minimum 2 caractères et maximum 30")
	@Size(min = 2, max = 30)
	@Column(unique = true)
	private String pseudo;
	@NonNull()
	@Column(unique = true)
	@Email(message = "merci de saisir une adresse mail correcte")
	private String email;
	@Size(min = 4)
	@NotEmpty(message = "votre mot de passe doit contenir minimum 4 caratères")
	private String password;
	
	// un utilisateur a une collection de sites
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
	private Collection<SiteEscalade> siteEscalades;
	
	// un utilisateur a une collection de topos
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	private Collection<Topo> topos;
	 
	// un utilisateur a une collection de reservations
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
	private Collection<Reservation> reservations;
	
	// un utilisateur a une collection de commentaires
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Commentaire>commentaires;

	// collection de rôles
	@ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Collection<RoleEnum> roles;
	
	// constructeur par défaut
	public UserGrimp () {
		this.roles = Collections.singletonList(RoleEnum.USER);
	}

	// constructeur avec paramètres
	public UserGrimp(long idUserGrimp,
			@NotEmpty(message = "votre pseudo doit contenir minimum 2 caractères et maximum 30") @Size(min = 2, max = 30) String pseudo,
			@Email(message = "merci de saisir une adresse mail correcte") String email,
			@Size(min = 4) @NotEmpty(message = "votre mot de passe doit contenir minimum 4 caratères") String password,
			Collection<SiteEscalade> siteEscalades, Collection<Topo> topos, Collection<Reservation> reservations,
			Collection<Commentaire> commentaires, Collection<RoleEnum> roles) {
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

	//getAuthorities() permet de récupérer les rôles de l’utilisateur dans un format que Spring Security peut interpréter
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		String roles = org.springframework.util.StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }

	// getters and setters
	public long getIdUserGrimp() {
		return idUserGrimp;
	}

	public void setIdUserGrimp(long idUserGrimp) {
		this.idUserGrimp = idUserGrimp;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<SiteEscalade> getSiteEscalades() {
		return siteEscalades;
	}

	public void setSiteEscalades(Collection<SiteEscalade> siteEscalades) {
		this.siteEscalades = siteEscalades;
	}

	public Collection<RoleEnum> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEnum> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Topo> getTopos() {
		return topos;
	}

	public void setTopos(Collection<Topo> topos) {
		this.topos = topos;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(Collection<Reservation> reservations) {
	this.reservations = reservations;	
	}

	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Collection<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getUsername() {
		
		return pseudo;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}