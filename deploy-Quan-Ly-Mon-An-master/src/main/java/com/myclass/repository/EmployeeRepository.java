package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myclass.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT u FROM Employee u WHERE u.email= :email ")
	Employee findByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM Employee u WHERE isDelete = false")
	public List<Employee> myFindAll();
	
	@Query("SELECT u FROM Employee u WHERE isDelete = true")
	public List<Employee> findAllIsDelete();
}
