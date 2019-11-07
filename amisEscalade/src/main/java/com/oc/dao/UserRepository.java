package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.oc.entities.UserGrimp;



public interface UserRepository extends JpaRepository<UserGrimp, Long> {

}
