package com.oc.web;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.ReservationRepository;
import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.Reservation;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.ReservationForm;



@Controller
public class ReservationController {
	
	// injections repositories
	@Autowired
	private ReservationRepository reservationRepository;	
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	@Autowired
	private TopoRepository topoRepository;
	
	// get and post Mapping
	/*============== #Demandes Crétation ======================*/
	@GetMapping("/reservation/{idTopo}/demande")
	public String sendResa(@ModelAttribute("reservationForm") ReservationForm reservationForm, Model model, @PathVariable("idTopo") long idTopo,
			final RedirectAttributes redirectAttributes) {
				
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Topo tpo = topoRepository.findById(idTopo).get();
		model.addAttribute("tpo", tpo);
		UserGrimp userGrimp = userGrimpRepository.findById(tpo.getUserGrimp().getIdUserGrimp()).get();
		
		//recupere la date du jour
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		
		Reservation newReservation = new Reservation();
		newReservation.setEmprunteur(usr.getPseudo());
		newReservation.setProprietaireTopo(userGrimp.getPseudo());
		newReservation.setNomDuTopoResa(tpo.getName());
		newReservation.setDateDeLaDemande(date);
		newReservation.setEmprunteur(usr.getPseudo());
		newReservation.setAccepterDemande(false);
		newReservation.setDemandeEnCours(true);
		newReservation.setTopo(tpo);
		reservationRepository.save(newReservation);
		
		return"redirect:/index";
	}
	

}
