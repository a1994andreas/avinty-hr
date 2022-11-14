package com.avinty.hr.services;

import com.avinty.hr.models.Employee;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface EmployeeService {
	List<Employee> getAllEmployees();

	Employee addNewEmployee(Employee employee) throws ValidationException;
}
