package com.avinty.hr.repositories;

import com.avinty.hr.models.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	List<Employee> findAll();

	Employee findByEmail(String email);

	@Modifying
	@Transactional
	@Query("update Employee emp set emp.dep_id = null where dep_id = :id")
	void removeEmployeesDepartment(@Param("id") int id);
}
