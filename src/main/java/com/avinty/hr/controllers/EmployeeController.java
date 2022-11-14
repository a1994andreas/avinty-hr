package com.avinty.hr.controllers;

import com.avinty.hr.models.Employee;
import com.avinty.hr.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class EmployeeController extends BaseController {
	private final EmployeeService employeeService;

	@GetMapping(value = "/employees", produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@PostMapping(value = "/employees", consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> addNewEmployee(@Valid @RequestBody Employee employee) throws ValidationException {
		return ResponseEntity.ok(employeeService.addNewEmployee(employee));
	}

}
