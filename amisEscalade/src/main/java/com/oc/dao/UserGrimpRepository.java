package com.oc.dao;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.UserGrimp;



@Repository
public interface UserGrimpRepository extends JpaRepository<UserGrimp, Long> {

	UserGrimp findByPseudo(String pseudo);

}
