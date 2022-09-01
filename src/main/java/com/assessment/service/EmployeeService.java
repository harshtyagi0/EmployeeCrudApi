package com.assessment.service;

import java.util.List;

import com.assessment.model.EmployeeModel;

public interface EmployeeService {
	
	List<EmployeeModel> getAllEmployees();
	
	EmployeeModel getEmployeeById(int id);
	
	boolean createEmployee(EmployeeModel employeeModel);
	
	boolean updateEmployee( EmployeeModel employeeDetails);
	
	boolean deleteEmployeeById(int id);
	

}

