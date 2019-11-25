package com.oc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.Longueur;
import com.oc.entities.Voie;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur, Long> {
	@Query("select lon from Longueur lon where lon.distance like:x")
	public Page<Longueur> chercher(@Param("x")String motCle, Pageable pageable);
}
