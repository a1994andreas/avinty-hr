package com.avinty.hr.listeners;

import com.avinty.hr.models.Department;
import com.avinty.hr.repositories.EmployeeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PreRemove;

@Component
@NoArgsConstructor
public class DepartmentListener {
	private static EmployeeRepository employeeRepository;

	@Autowired
	public void setMyService (EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}

	@PreRemove
	public void removeAssociationsWithEmployees(Department department) {
		employeeRepository.removeEmployeesDepartment(department.getId());
	}
}
