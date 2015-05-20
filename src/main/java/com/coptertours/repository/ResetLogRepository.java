package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.ResetLog;
import com.coptertours.options.ResetItem;

public interface ResetLogRepository extends JpaRepository<ResetLog, Long> {
	List<ResetLog> findByAircraftIdAndResetItem(Long aircraftId, ResetItem resetItem);
}
