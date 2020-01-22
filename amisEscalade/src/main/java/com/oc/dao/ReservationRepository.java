package com.oc.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.oc.entities.Reservation;

/**
 * The Interface ReservationRepository.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	 
	  /**
  	 * Gets the mes demandes.
  	 *
  	 * @param id the id
  	 * @return the mes demandes
  	 */
  	// liste des demandes de topo par propriétaire
	  @Query("SELECT r FROM Reservation r INNER JOIN FETCH r.topo t WHERE t.userGrimp.idUserGrimp = :x AND r.demandeEnCours=true")
	  public List<Reservation> getMesDemandes(@Param("x")long id);
	  
	  /**
  	 * Gets the demandes accepted.
  	 *
  	 * @param id the id
  	 * @return the demandes accepted
  	 */
  	// liste des demandes acceptées
	  @Query("SELECT ok FROM Reservation ok INNER JOIN FETCH ok.topo tp WHERE tp.userGrimp.idUserGrimp = :x AND ok.demandeEnCours=false AND ok.accepterDemande=true AND tp.dispo=false AND ok.close = false")
	  public List<Reservation> getDemandesAccepted(@Param("x")long id);
	  
	  /**
  	 * Gets the demande encours.
  	 *
  	 * @param idUser the id user
  	 * @param idTopo the id topo
  	 * @return the demande encours
  	 */
  	// liste des demandes en cours
	  @Query("SELECT k FROM Reservation k WHERE k.userGrimp.idUserGrimp = :idUser AND k.topo.idTopo = :idTopo AND k.close = false")
	  public List<Reservation>getDemandeEncours(@Param("idUser")long idUser, @Param("idTopo")long idTopo);
	  
	  /**
  	 * Gets the demande en cours by topo.
  	 *
  	 * @param idTopo the id topo
  	 * @return the demande en cours by topo
  	 */
  	// liste des demandes en cours par topo
	  @Query("SELECT k FROM Reservation k WHERE  k.demandeEnCours=true AND k.topo.idTopo = :idTopo ")
	  public List<Reservation>getDemandeEnCoursByTopo(@Param("idTopo")long idTopo);
	  
	  /**
  	 * Gets the mes demandes emises.
  	 *
  	 * @param idUser the id user
  	 * @return the mes demandes emises
  	 */
  	//liste des demandes emises par un utilisateur
	  @Query("SELECT u FROM Reservation u WHERE u.userGrimp.idUserGrimp = :x")
	  public List<Reservation> getMesDemandesEmises(@Param("x")long idUser);
	  	  
}
