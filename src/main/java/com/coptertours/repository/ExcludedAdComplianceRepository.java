package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.ExcludedAdCompliance;

public interface ExcludedAdComplianceRepository extends JpaRepository<ExcludedAdCompliance, Long> {

	List<ExcludedAdCompliance> deleteByAircraftId(Long aircraftId);
}
