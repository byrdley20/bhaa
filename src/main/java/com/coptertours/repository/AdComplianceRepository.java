package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Model;

public interface AdComplianceRepository extends BaseRepository<AdCompliance> {
	List<AdCompliance> findByModel(Model model, Sort sort);

	List<AdCompliance> findByModelAndActiveTrue(Model model, Sort sort);

	List<AdCompliance> findByModelAndDailyAndActiveTrue(Model model, Boolean daily, Sort sort);
}
