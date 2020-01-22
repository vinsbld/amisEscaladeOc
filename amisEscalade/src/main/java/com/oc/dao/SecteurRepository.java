package com.oc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Secteur;


/**
 * The Interface SecteurRepository.
 */
@Repository
public interface SecteurRepository extends CrudRepository<Secteur, Long> {
	
	/**
	 * Find by site.
	 *
	 * @param idSite the id site
	 * @return the iterable
	 */
	// trouver un secteur appartenant à un site d'escalade
@Query("select sec from Secteur sec where sec.siteEscalade.idSiteEscalade = :x")
public Iterable<Secteur> findBySite(@Param("x") long idSite);
}
