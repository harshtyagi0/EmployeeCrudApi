package com.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.entity.MyRoles;

@Repository
public interface RoleRepository extends JpaRepository<MyRoles, Integer> {
	Optional<MyRoles> findByRole(String role);

}
