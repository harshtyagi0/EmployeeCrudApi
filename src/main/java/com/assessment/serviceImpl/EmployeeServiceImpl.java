package com.assessment.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.assessment.exception.UserAlredyExist;
import com.assessment.model.Employee;
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
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach((employee) -> employees.add(employee));
		List<EmployeeModel> employeesModel = new ArrayList<EmployeeModel>();

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
		Employee employee = employeeRepository.findById(id).get();

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
	public EmployeeModel createEmployee(@Valid EmployeeModel employeeModel) {
		Employee e  = employeeRepository.findFirstByEmailId(employeeModel.getEmail());
		if(Objects.nonNull(e)) {
			throw new UserAlredyExist("User Alredy Exist with email : "+e.getEmail());
		}
		Employee employee = new Employee();
		employee.setName(employeeModel.getName());
		employee.setEmail(employeeModel.getEmail());
		employee.setDesignation(employeeModel.getDesignation());
		employee.setPhone(employeeModel.getPhone());
		employee.setSalary(employeeModel.getSalary());
		employee.setTech(employeeModel.getTech());
		employeeRepository.save(employee);
		return employeeModel;

	}

	@Override
	public EmployeeModel updateEmployee(int id, EmployeeModel employeeDetails) {
		Employee updateEmployee = null;

		updateEmployee = employeeRepository.findById(id).get();

		updateEmployee.setName(employeeDetails.getName());
		updateEmployee.setEmail(employeeDetails.getEmail());
		updateEmployee.setDesignation(employeeDetails.getDesignation());
		updateEmployee.setPhone(employeeDetails.getPhone());
		updateEmployee.setSalary(employeeDetails.getSalary());
		updateEmployee.setTech(employeeDetails.getTech());

		employeeRepository.save(updateEmployee);
		return employeeDetails;
	}

	@Override
	public int deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		return id;

	}

}
