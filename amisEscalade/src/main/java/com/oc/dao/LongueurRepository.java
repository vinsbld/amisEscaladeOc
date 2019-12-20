package com.oc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Longueur;

@Repository
public interface LongueurRepository extends CrudRepository<Longueur, Long> {
@Query("select longr from Longueur longr where longr.voie.idVoie = :x")
Iterable<Longueur> findByVoie(@Param("x") long idVoie);
}
