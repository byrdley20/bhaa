package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
	List<Aircraft> findByAircraftNumber(String aircraftNumber);
}
