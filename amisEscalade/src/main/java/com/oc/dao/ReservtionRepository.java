package com.oc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oc.entities.Reservation;

public interface ReservtionRepository extends JpaRepository<Reservation, Long>{
	
public List<Reservation> findByProprietaire(String pseudo);

public Reservation findByTopoId(long idTopo);

}
