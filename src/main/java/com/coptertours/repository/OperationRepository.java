package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	List<Operation> findByName(String name);
}
