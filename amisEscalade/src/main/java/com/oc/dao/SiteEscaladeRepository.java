package com.oc.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.entities.SiteEscalade;

@Repository
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>{	
	  @Query("select site from SiteEscalade site where site.nomSiteEscalade like:x")
	 public Page<SiteEscalade> chercher(@Param("x")String motCle, Pageable pageable);
	
	
}
