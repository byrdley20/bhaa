package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.MaintenanceType;

public interface MaintenanceTypeRepository extends JpaRepository<MaintenanceType, Long> {
	List<MaintenanceType> findByName(String name);
}
