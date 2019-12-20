package com.oc.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Secteur;


@Repository
public interface SecteurRepository extends CrudRepository<Secteur, Long> {
@Query("select sec from Secteur sec where sec.siteEscalade.idSiteEscalade = :x")
public Iterable<Secteur> findBySite(@Param("x") long idSite);
}
