package com.tyss.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tyss.entity.Product;

public interface ProdRepo extends JpaRepository<Product, Integer> {

	

	
}
