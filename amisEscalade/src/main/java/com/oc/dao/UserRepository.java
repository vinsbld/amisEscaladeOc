package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oc.entities.UserGrimp;



public interface UserRepository extends JpaRepository<UserGrimp, Long> {

}
