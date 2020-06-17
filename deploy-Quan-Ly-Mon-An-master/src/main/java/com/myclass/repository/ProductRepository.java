package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.ProductDto;
import com.myclass.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT u FROM Product u WHERE isDelete = false")
	public List<Product> myFindAll();

	@Query("SELECT new com.myclass.dto.ProductDto(u.id,u.name, u.imageUrl) FROM Product u WHERE u.imageUrl = :imageUrl")
	public ProductDto findFileByName(@RequestParam("imageUrl") String imageUrl);
}
