package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coptertours.domain.User;
import com.coptertours.options.Role;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUsername(String username);

	List<User> findByRole(Role role, Sort sort);
}
