package com.assessment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
	private ResponseEntity<List<EmployeeModel>> getAll() {
		return ResponseEntity.ok().body(emService.getAllEmployees());
	}

	@GetMapping("/{id}")
	private ResponseEntity<EmployeeModel> getEmployee(@PathVariable("id") int id) {
		return ResponseEntity.ok().body(emService.getEmployeeById(id));
	}

	@PostMapping
	private ResponseEntity<EmployeeModel> saveEmp(@Validated @RequestBody EmployeeModel employee) {
		return ResponseEntity.ok().body(emService.createEmployee(employee));
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<EmployeeModel> updateEmp(@Validated @PathVariable("id") int id, @RequestBody EmployeeModel employeeDetails) {
		return ResponseEntity.ok().body(emService.updateEmployee(id, employeeDetails));
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Integer> deleteEmp(@PathVariable("id") int id) {
		return ResponseEntity.ok().body(emService.deleteEmployeeById(id));
	}

}
