package com.avinty.hr.controllers;

import com.avinty.hr.models.Department;
import com.avinty.hr.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class DepartmentController extends BaseController{
	private final DepartmentService departmentService;

	@GetMapping( value = "/department", produces = { APPLICATION_JSON_VALUE })
	public List<String> getDepartmentByName(@RequestParam("name") String departmentName){
		return departmentService.getDepartmentByName(departmentName);
	}

	@PostMapping(value = "/department", consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
	public Department addDepartment(@Valid @RequestBody Department department) throws ValidationException {
		return departmentService.addDepartment(department);
	}

	@DeleteMapping(value = "/department/{id}", produces = { APPLICATION_JSON_VALUE })
	public void deleteDepartment(@PathVariable("id") Integer id) throws ValidationException{
		departmentService.deleteDepartment(id);
	}

	@GetMapping(value = "/dep-emp", produces = { APPLICATION_JSON_VALUE })
	public List<Department> getEmployeesPerDepartment(){
		return departmentService.getEmployeesPerDepartment();
	}
}
