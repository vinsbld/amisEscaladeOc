package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.entities.Secteur;


@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {

}
