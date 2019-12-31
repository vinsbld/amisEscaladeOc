package com.oc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.oc.entities.Commentaire;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{
	
	// trouver un commentaire appartenant à un site (par id)
	@Query("SELECT com FROM Commentaire com WHERE com.siteEscalade.idSiteEscalade = :x")
	public Iterable<Commentaire> findComBySite(@Param("x") long id);
	
	// touver un commentaire appartenant un utilisateur (par pseudo)
	@Query("SELECT com FROM Commentaire com WHERE com.userGrimp.pseudo = :x")
	public Iterable<Commentaire> findComByUserGrimpName(@Param("x") String pseudo);
	
}
