package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	List<Location> findByName(String name);
}
