package com.coptertours.repository;

import java.util.List;

import com.coptertours.domain.Operation;

public interface OperationRepository extends BaseRepository<Operation> {
	List<Operation> findByName(String name);
}
