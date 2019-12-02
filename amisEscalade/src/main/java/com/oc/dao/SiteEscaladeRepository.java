package com.oc.dao;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oc.entities.SiteEscalade;

@Repository
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>{	
	
}
