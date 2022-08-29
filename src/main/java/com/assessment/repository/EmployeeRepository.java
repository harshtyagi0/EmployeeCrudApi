package com.assessment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
