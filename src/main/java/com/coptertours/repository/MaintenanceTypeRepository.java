package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.coptertours.domain.MaintenanceType;
import com.coptertours.domain.Model;

public interface MaintenanceTypeRepository extends BaseRepository<MaintenanceType> {
	List<MaintenanceType> findByName(String name);

	List<MaintenanceType> findByModel(Model model, Sort sort);

	List<MaintenanceType> findByModelAndActiveTrue(Model model, Sort sort);

	List<MaintenanceType> findByModelAndShowOnDashboardTrueAndActiveTrue(Model model, Sort sort);
}
