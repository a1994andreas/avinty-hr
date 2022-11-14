package com.avinty.hr.services.impl;

import com.avinty.hr.models.Employee;
import com.avinty.hr.repositories.EmployeeRepository;
import com.avinty.hr.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	public Employee addNewEmployee(Employee employee) throws ValidationException {
		if (employeeRepository.findByEmail(employee.getEmail()) != null) {
			throw new ValidationException("Email already exists");
		}

		return employeeRepository.save(employee);
	}
}
