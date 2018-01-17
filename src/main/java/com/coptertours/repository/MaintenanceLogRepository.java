package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.MaintenanceLog;

public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {
	List<MaintenanceLog> findByAircraftId(Long aircraftId);

	MaintenanceLog findByAircraftIdAndMaintenanceTypeId(Long aircraftId, Long maintenanceTypeId);
}
