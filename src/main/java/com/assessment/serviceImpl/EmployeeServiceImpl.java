package com.assessment.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
		try {
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return employeesModel;
	}

	@Override
	public EmployeeModel getEmployeeById(int id) {
		Employee employee = null;

		try {
			employee = employeeRepository.findById(id).get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

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
	public Boolean createEmployee(EmployeeModel employeeModel) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public Boolean updateEmployee(int id, EmployeeModel employeeDetails) {
		Employee updateEmployee = null;
		try {
			updateEmployee = employeeRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateEmployee.setName(employeeDetails.getName());
		updateEmployee.setEmail(employeeDetails.getEmail());
		updateEmployee.setDesignation(employeeDetails.getDesignation());
		updateEmployee.setPhone(employeeDetails.getPhone());
		updateEmployee.setSalary(employeeDetails.getSalary());
		updateEmployee.setTech(employeeDetails.getTech());
		try {
			employeeRepository.save(updateEmployee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteEmployeeById(int id) {
		try {
			employeeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
