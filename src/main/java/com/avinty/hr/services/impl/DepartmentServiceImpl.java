package com.avinty.hr.services.impl;

import com.avinty.hr.models.Department;
import com.avinty.hr.repositories.DepartmentRepository;
import com.avinty.hr.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.xml.bind.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository departmentRepository;

	public List<String> getDepartmentByName(String departmentName){
		return departmentRepository.findByNameContaining(departmentName).stream().map(Department::getName).collect(Collectors.toList());
	}

	public Department addDepartment(Department department) throws ValidationException{
		if(departmentRepository.findByName(department.getName()) != null){
			throw new ValidationException("Department Name already exists");
		}

		return departmentRepository.save(department);
	}

	public void deleteDepartment(Integer id) throws ValidationException{
		if(!departmentRepository.findById(id).isPresent()){
			throw new ValidationException("Department doesn't exist");
		}

		departmentRepository.deleteById(id);
	}

	public List<Department> getEmployeesPerDepartment(){
		return departmentRepository.findAll();
	}
}
