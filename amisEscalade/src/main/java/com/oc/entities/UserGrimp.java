package com.oc.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.oc.security.BCryptManagerUtil;



@Entity
public class UserGrimp implements UserDetails {
	@Id @GeneratedValue
	private long idUserGrimp;
	@NonNull
	@Size(min = 2, max = 30)
	@Column(unique = true)
	private String pseudo;
	@NonNull
	@Column(unique = true)
	private String email;
	@Size(min = 4)
	@NonNull
	private String password;
	
	@OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY)
	private Collection<SiteEscalade>siteEscalades;
	
	
	  @OneToMany(mappedBy = "userGrimp", fetch = FetchType.LAZY) 
	  private Collection<Topo>topos;
	 
	
	@ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
	
	@Cascade(value = CascadeType.REMOVE)
	
	@JoinTable(
			indexes = {@Index(name = "INDEX_USERGRIMP_ROLE", columnList = "id_user_grimp")},
			name = "roles",
			joinColumns = @JoinColumn(name = "id_user_grimp")
			)
	
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<RoleEnum> roles;
	
	
	
	
	public UserGrimp () {
		this.roles = Collections.singletonList(RoleEnum.USER);
	}




	public UserGrimp(long idUserGrimp, @Size(min = 2, max = 30) String pseudo, String email,
			@Size(min = 4) String password, Collection<SiteEscalade> siteEscalades, Collection<RoleEnum> roles) {
		this.idUserGrimp = idUserGrimp;
		this.pseudo = pseudo;
		this.email = email;
		this.password = BCryptManagerUtil.passwordencoder().encode(password);
		this.siteEscalades = siteEscalades;
		this.roles = roles;
	}

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




	@Override
	public String getUsername() {
		
		return null;
	}




	@Override
	public boolean isAccountNonExpired() {
		
		return false;
	}




	@Override
	public boolean isAccountNonLocked() {
		
		return false;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		
		return false;
	}




	@Override
	public boolean isEnabled() {
		
		return false;
	}




	public Collection<Topo> getTopos() {
		return topos;
	}




	public void setTopos(Collection<Topo> topos) {
		this.topos = topos;
	}


	
}