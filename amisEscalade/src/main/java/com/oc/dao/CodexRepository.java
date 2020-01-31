package com.oc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oc.entities.Codex;

/**
 * The Interface CodexRepository.
 */
public interface CodexRepository extends CrudRepository<Codex, Long>{	
	/**
	 * Find all city.
	 *
	 * @return the list
	 */
	// trier les sites par nom de commune et par odre alphabétique
    @Query("select distinct cdx from Codex cdx order by cdx.commune asc ")
    public List<Codex> findAllCity();

}
