package com.assessment.service;

import java.util.List;

import com.assessment.model.EmployeeModel;

public interface EmployeeService {
	
	List<EmployeeModel> getAllEmployees();
	
	EmployeeModel getEmployeeById(int id);
	
	EmployeeModel createEmployee(EmployeeModel employeeModel);
	
	EmployeeModel updateEmployee(int id, EmployeeModel employeeDetails);
	
	int deleteEmployeeById(int id);
	

}
