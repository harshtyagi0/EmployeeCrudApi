package com.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assessment.enitity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {

	@Query("SELECT u FROM MyUser u WHERE u.userName= ?1")
	Optional<MyUser> findByName(String name);

}
