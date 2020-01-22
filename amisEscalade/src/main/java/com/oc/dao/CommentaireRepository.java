package com.oc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.oc.entities.Commentaire;

/**
 * The Interface CommentaireRepository.
 */
public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{
	
	/**
	 * Find com by site.
	 *
	 * @param id the id
	 * @return the iterable
	 */
	// trouver un commentaire appartenant à un site (par id)
	@Query("SELECT com FROM Commentaire com WHERE com.siteEscalade.idSiteEscalade = :x")
	public Iterable<Commentaire> findComBySite(@Param("x") long id);
	
	/**
	 * Find com by user grimp name.
	 *
	 * @param pseudo the pseudo
	 * @return the iterable
	 */
	// touver un commentaire appartenant un utilisateur (par pseudo)
	@Query("SELECT com FROM Commentaire com WHERE com.userGrimp.pseudo = :x")
	public Iterable<Commentaire> findComByUserGrimpName(@Param("x") String pseudo);
	
}
