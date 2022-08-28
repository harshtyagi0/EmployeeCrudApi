package com.assessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.EmployeeModel;
import com.assessment.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService emService;

	EmployeeController(EmployeeService emService) {
		this.emService = emService;
	}

	@GetMapping
	private List<EmployeeModel> getAll() {
		return emService.getAllEmployees();
	}

	@GetMapping("/{id}")
	private EmployeeModel getEmployee(@PathVariable("id") int id) {
		return emService.getEmployeeById(id);
	}

	@PostMapping
	private boolean saveEmp(@Valid @RequestBody EmployeeModel employee) {
		return emService.createEmployee(employee);
	}
	
	@PutMapping("/{id}")
	private boolean updateEmp(@Valid @PathVariable("id") int id, @RequestBody EmployeeModel employeeDetails) {
		return emService.updateEmployee(id, employeeDetails);
	}
	
	@DeleteMapping("/{id}")
	private boolean deleteEmp(@PathVariable("id") int id) {
		return emService.deleteEmployeeById(id);
	}

}
