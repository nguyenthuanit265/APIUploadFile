package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.DetailStatisticDto;
import com.myclass.dto.StatisticDto;
import com.myclass.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {	
	@Query("SELECT new com.myclass.dto.StatisticDto(year(b.dateBill), count(b.id), sum(b.price)) FROM Bill b GROUP BY year(b.dateBill)")
	List<StatisticDto> statisticAllByYear();

	
	@Query("SELECT new com.myclass.dto.DetailStatisticDto(month(b.dateBill), count(b.id), sum(b.price)) FROM Bill b WHERE year(b.dateBill)= :year")
	List<DetailStatisticDto> statisticByYear(@Param("year") int year);
}
