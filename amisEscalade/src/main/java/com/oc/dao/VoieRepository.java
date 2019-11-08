package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.entities.Voie;

@Repository
public interface VoieRepository extends JpaRepository<Voie, Long>{

}
