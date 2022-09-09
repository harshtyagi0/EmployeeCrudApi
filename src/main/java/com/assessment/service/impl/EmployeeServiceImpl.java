package com.assessment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assessment.enitity.Employee;
import com.assessment.exception.UserAlredyExist;
import com.assessment.exception.UserNotExist;
import com.assessment.model.EmployeeModel;
import com.assessment.repository.EmployeeRepository;
import com.assessment.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeModel> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeModel> employeesModel = new ArrayList<>();

		for (Employee e : employees) {
			EmployeeModel em = new EmployeeModel();
			em.setId(e.getId());
			em.setName(e.getName());
			em.setDesignation(e.getDesignation());
			em.setPhone(e.getPhone());
			em.setSalary(e.getSalary());
			em.setTech(e.getTech());
			em.setEmail(e.getEmail());
			employeesModel.add(em);
		}
		return employeesModel;
	}

	@Override
	public EmployeeModel getEmployeeById(int id) {
		Optional<Employee> e = employeeRepository.findById(id);
		if (!e.isPresent())
			throw new UserNotExist("User doesn't exist with id : " + id);

		Employee employee = e.get();
		EmployeeModel em = new EmployeeModel();
		em.setId(employee.getId());
		em.setDesignation(employee.getDesignation());
		em.setEmail(employee.getEmail());
		em.setName(employee.getName());
		em.setPhone(employee.getPhone());
		em.setSalary(employee.getSalary());
		em.setTech(employee.getTech());
		return em;
	}

	@Override
	public boolean createEmployee(EmployeeModel employeeModel) {
		Employee e = employeeRepository.findFirstByEmailId(employeeModel.getEmail());
		if (Objects.nonNull(e)) {
			throw new UserAlredyExist("User Alredy Exist with email : " + e.getEmail());
		}
		Employee employee = new Employee();
		employee.setName(employeeModel.getName());
		employee.setEmail(employeeModel.getEmail());
		employee.setDesignation(employeeModel.getDesignation());
		employee.setPhone(employeeModel.getPhone());
		employee.setSalary(employeeModel.getSalary());
		employee.setTech(employeeModel.getTech());
		try {
			employeeRepository.save(employee);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean updateEmployee(EmployeeModel employeeDetails) {
		Optional<Employee> updateEmployee = employeeRepository.findById(employeeDetails.getId());
		Employee updation = null;
		if (updateEmployee.isPresent()) {
			updation = updateEmployee.get();
			updation.setName(employeeDetails.getName());
			updation.setEmail(employeeDetails.getEmail());
			updation.setDesignation(employeeDetails.getDesignation());
			updation.setPhone(employeeDetails.getPhone());
			updation.setSalary(employeeDetails.getSalary());
			updation.setTech(employeeDetails.getTech());
			employeeRepository.save(updation);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteEmployeeById(int id) {
		if (employeeRepository.findById(id).isPresent()) {
			employeeRepository.deleteById(id);
			return true;
		}
		throw new UserNotExist("Unable to perform delete as User doesn't exist with id : " + id);

	}

}
