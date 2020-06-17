package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclass.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
