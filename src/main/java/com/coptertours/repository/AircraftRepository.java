package com.coptertours.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coptertours.domain.Aircraft;
import com.coptertours.options.ResetItem;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
	List<Aircraft> findByAircraftNumber(String aircraftNumber);

	List<Aircraft> findByInServiceTrue(Sort sort);

	@Query("select sum(rl.hobbs) from ResetLog rl where rl.aircraftId = ?1 and rl.resetItem = ?2")
	BigDecimal findTotalOffsetByAircraftAndItem(Long aircraftId, ResetItem resetItem);
}
