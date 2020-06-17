package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Employee;
import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE isDelete = false")
	public List<User> myFindAll();
	
	
	@Query("SELECT u FROM User u WHERE isDelete = true")
	public List<User> findAllIsDelete();
}
