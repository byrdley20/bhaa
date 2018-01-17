package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.Component;
import com.coptertours.domain.MaintenanceType;

public interface ComponentRepository extends JpaRepository<Component, Long> {

	List<Component> findByAircraft(Aircraft aircraft, Sort sort);

	Component findByAircraftAndMaintenanceType(Aircraft aircraft, MaintenanceType maintenanceType);
}
