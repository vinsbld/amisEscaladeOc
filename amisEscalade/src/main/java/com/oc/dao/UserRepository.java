package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oc.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
