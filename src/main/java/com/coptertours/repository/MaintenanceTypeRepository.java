package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.domain.Model;

public interface MaintenanceTypeRepository extends BaseRepository<MaintenanceType> {
	List<MaintenanceType> findByName(String name);

	List<MaintenanceType> findByModel(Model model, Sort sort);

	List<MaintenanceType> findByModelAndActiveTrue(Model model, Sort sort);

	@Query(value = "select * from maintenance_type mt left outer join excluded_maintenance_type emt on emt.maintenance_type_id=mt.id and emt.aircraft_id=?2 where mt.model_id=?1 and emt.id is null and mt.active=1 order by mt.name", nativeQuery = true)
	List<MaintenanceType> findByModelAndAircraftAndActiveTrue(Model model, Aircraft aircraft);

	@Query(value = "select * from maintenance_type mt left outer join excluded_maintenance_type emt on emt.maintenance_type_id=mt.id and emt.aircraft_id=?2 where mt.model_id=?1 and emt.id is null and mt.show_on_dashboard=1 and mt.active=1 order by mt.name", nativeQuery = true)
	List<MaintenanceType> findByModelAndAircraftAndShowOnDashboardTrueAndActiveTrue(Model model, Aircraft aircraft);
}
