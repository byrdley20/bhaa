package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
	List<Maintenance> findByName(String name);
}
