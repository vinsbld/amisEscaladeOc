package com.oc.dao;






import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.oc.entities.SiteEscalade;

@RepositoryRestResource
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>{	

}
