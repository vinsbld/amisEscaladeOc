package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.entities.Topo;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long>{

}
