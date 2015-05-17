package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Model;

public interface AdComplianceRepository extends JpaRepository<AdCompliance, Long> {
	List<AdCompliance> findByModel(Model model, Sort sort);

	List<AdCompliance> findByModelAndDaily(Model model, Boolean daily, Sort sort);
}
