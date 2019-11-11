package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.entities.Longueur;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur, Long> {

}
