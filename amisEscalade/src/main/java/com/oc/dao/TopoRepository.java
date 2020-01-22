package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oc.entities.Topo;

/**
 * The Interface TopoRepository.
 */
@RepositoryRestResource
public interface TopoRepository extends JpaRepository<Topo, Long>{
	
	/**
	 * Find by user G.
	 *
	 * @param id the id
	 * @return the iterable
	 */
	// trouver un topo appartenant à un utilistateur (par idUtilisateur)
	@Query("SELECT top FROM Topo top WHERE top.userGrimp.idUserGrimp = :x")
	public Iterable<Topo> findByUserG(@Param("x") long id);

	/**
	 * Gets the topo name.
	 *
	 * @param name the name
	 * @return the topo name
	 */
	// trouver un topo par son nom
	@Query("select tpo from Topo tpo where tpo.name = :x")
	public Topo getTopoName(@Param("x") String name);
	
}
