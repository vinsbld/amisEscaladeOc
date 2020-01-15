package com.oc.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.oc.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	 
	  // liste des demandes de topo par propriétaire
	  @Query("SELECT r FROM Reservation r INNER JOIN FETCH r.topo t WHERE t.userGrimp.idUserGrimp = :x AND r.demandeEnCours=true")
	  public List<Reservation> getMesDemandes(@Param("x")long id);
	  
	  // liste des demandes acceptées
	  @Query("SELECT ok FROM Reservation ok INNER JOIN FETCH ok.topo tp WHERE tp.userGrimp.idUserGrimp = :x AND ok.demandeEnCours=false and ok.accepterDemande=true AND tp.dispo=false")
	  public List<Reservation> getDemandesAccepted(@Param("x")long id);
	  
	  @Query("SELECT k FROM Reservation k WHERE k.userGrimp.idUserGrimp = :idUser AND k.topo.idTopo = :idTopo AND k.close = false")
	  public List<Reservation>getDemandeEncours(@Param("idUser")long idUser, @Param("idTopo")long idTopo);
	  
	  @Query("SELECT k FROM Reservation k WHERE k.demandeEnCours=true AND k.topo.idTopo = :idTopo ")
	  public List<Reservation>getDemandeEnCoursByTopo(@Param("idTopo")long idTopo);
	  
	  @Query("SELECT u FROM Reservation u WHERE u.userGrimp.idUserGrimp = :x")
	  public List<Reservation> getMesDemandesEmises(@Param("x")long idUser);
	  
	  
	  
}
