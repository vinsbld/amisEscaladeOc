package com.oc.dao;






import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.oc.entities.SiteEscalade;

@RepositoryRestResource
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>{	
@Query("SELECT sit FROM SiteEscalade sit WHERE sit.userGrimp.idUserGrimp = :x")
public List<SiteEscalade> findByUserGrimp(@Param("x") long id);
}
