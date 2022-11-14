package com.avinty.hr.repositories;

import com.avinty.hr.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	List<Department> findAll();

	Department findByName(String name);

	List<Department> findByNameContaining(String name);
}
