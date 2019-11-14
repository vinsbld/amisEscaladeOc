package com.oc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
