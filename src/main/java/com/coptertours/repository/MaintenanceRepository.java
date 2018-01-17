package com.coptertours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.MaintenanceType;

public interface MaintenanceRepository extends JpaRepository<MaintenanceType, Long> {
	// List<MaintenanceType> findByModel(Model model);
}
