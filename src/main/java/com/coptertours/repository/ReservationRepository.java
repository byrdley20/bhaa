package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByFamilyName(String familyName);
}
