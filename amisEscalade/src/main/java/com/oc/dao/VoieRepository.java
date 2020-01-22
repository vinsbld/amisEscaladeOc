package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Voie;

/**
 * The Interface VoieRepository.
 */
@Repository
public interface VoieRepository extends JpaRepository<Voie, Long>{

/**
 * Find by secteur.
 *
 * @param idVoie the id voie
 * @return the iterable
 */
	//trouver une voie appartenant à un secteur
@Query("select vo from Voie vo where vo.secteur.idSecteur = :x")
public Iterable<Voie> findBySecteur(@Param("x") long idVoie);
}
