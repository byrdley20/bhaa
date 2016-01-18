package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Model;

public interface AdComplianceRepository extends BaseRepository<AdCompliance> {
	List<AdCompliance> findByModel(Model model, Sort sort);

	List<AdCompliance> findByModelAndActiveTrue(Model model, Sort sort);

	List<AdCompliance> findByModelAndDailyAndActiveTrue(Model model, Boolean daily, Sort sort);

	@Query(value = "select adc.* from ad_compliance adc left outer join excluded_ad_compliance eadc on eadc.ad_compliance_id=adc.id and eadc.aircraft_id=?2 where adc.model_id=?1 and eadc.id is null and adc.active=1", nativeQuery = true)
	List<AdCompliance> findByModelAndAircraftAndActiveTrue(long modelId, long aircraftId);

	@Query(value = "select adc.* from ad_compliance adc left outer join excluded_ad_compliance eadc on eadc.ad_compliance_id=adc.id and eadc.aircraft_id=?2 where adc.model_id=?1 and eadc.id is null and adc.daily=?3 and adc.active=1", nativeQuery = true)
	List<AdCompliance> findByModelAndAircraftAndDailyAndActiveTrue(long modelId, long aircraftId, Boolean daily);
}