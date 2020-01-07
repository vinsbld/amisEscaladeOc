package com.oc.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oc.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	
	  @Query(value="SELECT res FROM Reservation res INNER JOIN FETCH res.userGrimp usr WHERE usr.pseudo=:pseudo ORDER BY res.dateDeLaDemande desc", 
			  countQuery="SELECT count (res) FROM Reservation res INNER JOIN res.userGrimp usr WHERE usr.pseudo=:pseudo") 
	  public List<Reservation> resaList(@Param("pseudo")String pseudo);
	 

}
