package com.oc.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oc.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	@Query("SELECT res FROM Reservation res WHERE res.emprunteur = : pseudo")
	public List<Reservation> resaList(@Param("pseudo")String pseudo);


}
