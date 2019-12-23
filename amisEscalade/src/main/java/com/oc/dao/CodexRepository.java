package com.oc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oc.entities.Codex;

public interface CodexRepository extends CrudRepository<Codex, Long>{
	
// jointure par departements
@Query("SELECT distinct cdx.departement FROM Codex cdx INNER JOIN SiteEscalade site ON cdx.departement = site.departement")
public List<String> getDepartement();

// jointure par communes
@Query("SELECT distinct cdx.commune FROM Codex cdx INNER JOIN SiteEscalade site ON cdx.commune = site.ville")
public List<String> getVille();

// jointure par code postal
@Query("SELECT cdx.codePostal FROM Codex cdx INNER JOIN SiteEscalade site ON cdx.codePostal = site.codePostal")
public List<Long> getCdp();

}
