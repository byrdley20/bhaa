package com.coptertours.repository;

import java.util.List;

import com.coptertours.domain.Model;

public interface ModelRepository extends BaseRepository<Model> {
	List<Model> findByName(String name);

	Model findById(Long id);
}
