package com.oc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oc.entities.Topo;

@RepositoryRestResource
public interface TopoRepository extends JpaRepository<Topo, Long>{
@Query("SELECT top FROM Topo top WHERE top.userGrimp.idUserGrimp = :x")
public List<Topo> findByUserG(@Param("x") long id);
}
