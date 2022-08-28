package com.assessment.service;

import java.util.List;

import com.assessment.model.EmployeeModel;

public interface EmployeeService {
	
	List<EmployeeModel> getAllEmployees();
	
	EmployeeModel getEmployeeById(int id);
	
	Boolean createEmployee(EmployeeModel employeeModel);
	
	Boolean updateEmployee(int id, EmployeeModel employeeDetails);
	
	Boolean deleteEmployeeById(int id);
	

}
