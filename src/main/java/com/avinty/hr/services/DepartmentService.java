package com.avinty.hr.services;

import com.avinty.hr.models.Department;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface DepartmentService {
	List<String> getDepartmentByName(String departmentName);

	Department addDepartment(Department department) throws ValidationException;

	void deleteDepartment(Integer id) throws ValidationException;

	List<Department> getEmployeesPerDepartment();
}
