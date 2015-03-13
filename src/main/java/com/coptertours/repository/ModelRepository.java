package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
	List<Model> findByName(String name);
}
