package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.ExcludedMaintenanceType;

public interface ExcludedMaintenanceTypeRepository extends JpaRepository<ExcludedMaintenanceType, Long> {

	List<ExcludedMaintenanceType> deleteByAircraftId(Long aircraftId);
}
