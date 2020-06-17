package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.DetailBill;

@Repository
public interface DetailBillRepository extends JpaRepository<DetailBill, Integer> {
	
	@Query(value = "SELECT u FROM DetailBill u WHERE u.bill.id= :bill_id")
	 public List<DetailBill> getOneDetailBillById_Bill(@Param("bill_id") int id);

}
