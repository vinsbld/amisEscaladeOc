package com.oc.dao;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.UserGrimp;



@Repository
public interface UserGrimpRepository extends JpaRepository<UserGrimp, Long> {
	
	// trouver un utilisateur par son pseudo
	@Query("SELECT p FROM UserGrimp p WHERE p.pseudo = :x")
	UserGrimp findByPseudo(@Param("x") String pseudo);
	
	// trouver un utilisateur par son email
	@Query("SELECT e FROM UserGrimp e WHERE e.email = :x")
	public UserGrimp getUsrEmail(@Param("x") String email);

}
