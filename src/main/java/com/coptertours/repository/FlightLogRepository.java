package com.coptertours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.FlightLog;

public interface FlightLogRepository extends JpaRepository<FlightLog, Long> {
	// FlightLog findByAircraftId(Long aircraftId);
}
