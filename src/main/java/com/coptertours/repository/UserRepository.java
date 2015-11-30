package com.coptertours.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.coptertours.domain.User;
import com.coptertours.options.Role;

public interface UserRepository extends BaseRepository<User> {
	List<User> findByUsername(String username);

	List<User> findByRoleAndActiveTrue(Role role, Sort sort);
}
