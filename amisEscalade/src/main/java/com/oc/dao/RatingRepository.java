package com.oc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oc.entities.Rating;

/**
 * The Interface RatingRepository.
 */
@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>{
	
	/**
	 * Gets the difficulty.
	 *
	 * @return the difficulty
	 */
	//jointure pour les cotations des voies
 @Query("SELECT distinct r.cote FROM Rating r INNER JOIN Voie v ON r.cote = v.cotation") 
 public List<Rating> getDifficulty();

}
