package com.oc.metier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.dao.ReservationRepository;
import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.Reservation;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservtionRepository;	
	@Autowired
	private TopoRepository topoRepository;	
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	public void saveReservation(Reservation reservation,String pseudo, Long idTop) {
		
		reservtionRepository.save(reservation);
				
		
	}

}
