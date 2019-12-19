package com.oc.dao;






import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.SiteEscalade;

// hérite des méthodes de l'interface CrudRepository
@Repository
public interface SiteEscaladeRepository extends CrudRepository<SiteEscalade, Long>{	
// liste des sites d'escalades appartenants a un untilisateur
@Query("SELECT sit FROM SiteEscalade sit WHERE sit.userGrimp.idUserGrimp = :x")
public Iterable<SiteEscalade> findByUserGrimp(@Param("x") long id);
}
