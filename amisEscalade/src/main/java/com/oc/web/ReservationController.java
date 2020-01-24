package com.oc.web;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oc.dao.ReservationRepository;
import com.oc.dao.TopoRepository;
import com.oc.dao.UserGrimpRepository;
import com.oc.entities.Reservation;
import com.oc.entities.Topo;
import com.oc.entities.UserGrimp;
import com.oc.forms.ReservationForm;



/**
 * The Class ReservationController.
 */
@Controller
public class ReservationController {
	
	// injections repositories
	/** The reservation repository. */
	@Autowired
	private ReservationRepository reservationRepository;	
	
	/** The user grimp repository. */
	@Autowired
	private UserGrimpRepository userGrimpRepository;
	
	/** The topo repository. */
	@Autowired
	private TopoRepository topoRepository;
	
	// get and post Mapping
	/*============== #Demandes Crétation ======================*/
	/**
	 * Send resa.a reservation demand 
	 *
	 * @param reservationForm the reservation form
	 * @param model the model
	 * @param idTopo the id topo
	 * @param redirectAttributes the redirect attributes
	 * @return the topo
	 */
	@PostMapping("/reservation/{idTopo}/demande")
	public String sendResa(@ModelAttribute("reservationForm") ReservationForm reservationForm, Model model, @PathVariable("idTopo") long idTopo,
			final RedirectAttributes redirectAttributes) {
				
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Topo tpo = topoRepository.findById(idTopo).get();
		model.addAttribute("tpo", tpo);
		usr = userGrimpRepository.findById(usr.getIdUserGrimp()).get();
		model.addAttribute("us", usr);
		UserGrimp userGrimp = userGrimpRepository.findById(tpo.getUserGrimp().getIdUserGrimp()).get();
		model.addAttribute("user", userGrimp);
		
		//recupere la date du jour
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		
		Reservation newReservation = new Reservation();
		newReservation.setDateDeLaDemande(date);
		newReservation.setUserGrimp(usr);
		newReservation.setAccepterDemande(false);
		newReservation.setDemandeEnCours(true);
		newReservation.setClose(false);
		newReservation.setTopo(tpo);
		
		topoRepository.save(newReservation.getTopo());
		reservationRepository.save(newReservation);
		
		return"redirect:/topo";
	}
	
	/*============== #Affiche les Reservations ======================*/	
	/**
	 * Topo resa.display the user's reservations demands
	 *
	 * @param model the model
	 * @return the reservation_topo
	 */
	@GetMapping("/topo/mes_reservations")
	public String topoResa(Model model) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		List<Reservation> res = reservationRepository.getMesDemandes(usr.getIdUserGrimp());
		model.addAttribute("res", res);
		
		List<Reservation> rsok = reservationRepository.getDemandesAccepted(usr.getIdUserGrimp());
		model.addAttribute("rsok", rsok);
		
		List<Reservation> rS = reservationRepository.getMesDemandesEmises(usr.getIdUserGrimp());
		model.addAttribute("rS", rS);
		
		return "reservation_topo";
	}
	
	/*============== #Post les decisions de prêts ======================*/	
	/**
	 * Accept or not. the user's decision for a loan
	 *
	 * @param idResa the id resa
	 * @param model the model
	 * @param reservationForm the reservation form
	 * @param redirectAttributes the redirect attributes
	 * @return the mes_reservations
	 */
	@PostMapping("/topo/mes_reservations/ok")
	public String acceptOrNot(@RequestParam("idResa")long idResa, Model model, @ModelAttribute("reservationForm") ReservationForm reservationForm , final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		List<Reservation> res = reservationRepository.getMesDemandes(usr.getIdUserGrimp());
		model.addAttribute("res", res);
		
		Reservation newR = reservationRepository.findById(idResa).get();
		newR.setAccepterDemande(reservationForm.isAccepterDemande());
		reservationRepository.save(newR);
	
		if(newR.isAccepterDemande()) {
			newR.getTopo().setDispo(false);
			newR.setDemandeEnCours(false);
			newR.setClose(false);
			reservationRepository.save(newR);
			topoRepository.save(newR.getTopo());
			List<Reservation> rBt = reservationRepository.getDemandeEnCoursByTopo(newR.getTopo().getIdTopo());
				for(int i=0;i<rBt.size();i++) {
					if (!rBt.isEmpty()) {
						long id = rBt.get(i).getIdResa();
						Reservation r = reservationRepository.findById(id).get();
						r.setDemandeEnCours(false);
						r.setAccepterDemande(false);
						r.setClose(true);
						reservationRepository.save(r);
					}
			}
		}else {
			newR.setDemandeEnCours(false);
			newR.getTopo().setDispo(true);
			newR.setClose(true);
			reservationRepository.save(newR);
			topoRepository.save(newR.getTopo());			
		}
	
		return"redirect:/topo/mes_reservations";
	}
	
	/**
	 * Return reset.make a topo available again
	 *
	 * @param idResa the id resa
	 * @param model the model
	 * @param reservationForm the reservation form
	 * @param redirectAttributes the redirect attributes
	 * @return the mes_reservations
	 */
	@PostMapping("/topo/mes_reservations/reset")
	public String returnReset(@RequestParam("idResa")long idResa, Model model, @ModelAttribute("reservationForm") ReservationForm reservationForm , final RedirectAttributes redirectAttributes) {
		
		UserGrimp usr = (UserGrimp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usr", usr);
		
		List<Reservation> res = reservationRepository.getMesDemandes(usr.getIdUserGrimp());
		model.addAttribute("res", res);
		
		Reservation newR = reservationRepository.findById(idResa).get();
		newR.getTopo().setDispo(true);
		newR.setClose(true);
		reservationRepository.save(newR);
		topoRepository.save(newR.getTopo());		
		
		return"redirect:/topo/mes_reservations";
	}
}
