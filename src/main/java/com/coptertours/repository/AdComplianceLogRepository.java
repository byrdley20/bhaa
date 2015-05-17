package com.coptertours.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.AdComplianceLog;

public interface AdComplianceLogRepository extends JpaRepository<AdComplianceLog, Long> {
	List<AdComplianceLog> findByAircraftId(Long aircraftId);

	List<AdComplianceLog> findByAircraftIdAndAdComplianceIdAndComplyWithDateBetween(Long aircraftId, Long adComplianceId, Date startDate, Date endDate, Sort sort);
}
