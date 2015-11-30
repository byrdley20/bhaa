package com.coptertours.repository;

import java.util.List;

import com.coptertours.domain.Rating;

public interface RatingRepository extends BaseRepository<Rating> {
	List<Rating> findByName(String name);
}
