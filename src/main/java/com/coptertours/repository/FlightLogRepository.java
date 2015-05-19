package com.coptertours.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.FlightLog;

public interface FlightLogRepository extends JpaRepository<FlightLog, Long> {
	List<FlightLog> findByAircraft(Aircraft aircraft, Sort sort);

	List<FlightLog> findByAircraftAndDateBetween(Aircraft aircraft, Date startDate, Date endDate, Sort sort);

	@Query("select max(fl.hobbsEnd) from FlightLog fl where fl.aircraft = ?1")
	BigDecimal findMaxHobbsEndByAircraft(Aircraft aircraft);

	@Query("select sum(fl.starts) from FlightLog fl where fl.aircraft = ?1 and fl.date between ?2 and ?3")
	Integer findTotalStartsByAircraftAndDateBetween(Aircraft aircraft, Date dateBegin, Date dateEnd);
}
