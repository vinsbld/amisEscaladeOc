package com.oc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Secteur;


@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {
@Query("select sec from Secteur sec where sec.nomDuSecteur like:x")
public Page<Secteur> chercher(@Param("x")String motCle, Pageable pageable);
}
