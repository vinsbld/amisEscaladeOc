package com.oc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Voie;

@Repository
public interface VoieRepository extends JpaRepository<Voie, Long>{
@Query("select voi from Voie voi where voi.nomDeVoie like:x")
public Page<Voie> chercher(@Param("x")String motCle, Pageable pageable);
}
