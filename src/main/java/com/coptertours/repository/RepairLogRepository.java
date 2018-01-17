package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.RepairLog;

public interface RepairLogRepository extends JpaRepository<RepairLog, Long> {
	List<RepairLog> findByRepairDateIsNull();

	List<RepairLog> findByRepairDateIsNotNull();
}