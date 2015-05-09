package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.AdComplianceLog;

public interface AdComplianceLogRepository extends JpaRepository<AdComplianceLog, Long> {
	List<AdComplianceLog> findByAircraft(Long aircraftId, Sort sort);
}
