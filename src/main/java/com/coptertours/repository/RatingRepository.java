package com.coptertours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByName(String name);
}
