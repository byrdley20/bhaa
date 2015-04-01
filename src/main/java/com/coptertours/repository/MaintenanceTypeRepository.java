package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.MaintenanceType;
import com.coptertours.domain.Model;

public interface MaintenanceTypeRepository extends JpaRepository<MaintenanceType, Long> {
	List<MaintenanceType> findByName(String name);

	List<MaintenanceType> findByModel(Model model, Sort sort);
}
