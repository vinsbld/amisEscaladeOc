package com.oc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Voie;

@Repository
public interface VoieRepository extends JpaRepository<Voie, Long>{
@Query("select vo from Voie vo where vo.secteur.idSecteur = :x")
public List<Voie> findBySecteur(@Param("x") long id);
}
