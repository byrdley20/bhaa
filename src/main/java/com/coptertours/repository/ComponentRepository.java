package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.Component;

public interface ComponentRepository extends JpaRepository<Component, Long> {

	List<Component> findByAircraft(Aircraft aircraft, Sort sort);
}
