package com.oc.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.oc.entities.SiteEscalade;

@RepositoryRestResource
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>, JpaSpecificationExecutor<SiteEscalade>{	
	/* public List<SiteEscalade> findByName(String nomSiteEscalade); */
}
