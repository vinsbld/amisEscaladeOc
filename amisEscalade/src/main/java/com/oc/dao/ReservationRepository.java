package com.oc.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.oc.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{


}
