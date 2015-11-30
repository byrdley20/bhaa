package com.coptertours.repository;

import java.util.List;

import com.coptertours.domain.Location;

public interface LocationRepository extends BaseRepository<Location> {
	List<Location> findByName(String name);
}
