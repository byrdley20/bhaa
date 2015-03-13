package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.ReservationRepository;

@Controller
public class ReservationController {
	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping("/reservations.html")
	String reservations(Model model) {
		model.addAttribute("reservations", this.reservationRepository.findAll());
		return "reservations";
	}
}
