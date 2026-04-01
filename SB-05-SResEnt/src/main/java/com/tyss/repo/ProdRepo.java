package com.tyss.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tyss.entity.Product;

public interface ProdRepo extends JpaRepository<Product, Integer> {

	// ========== CUSTOM METHODS (No @Query needed) ==========

	// 1. Find products by exact name
	List<Product> findByName(String name);

	// 2. Find products by price greater than
	List<Product> findByPriceGreaterThan(double price);

	// 3. Find products by price between
	List<Product> findByPriceBetween(double minPrice, double maxPrice);

	// 4. Find products by name or price (OR condition)
	List<Product> findByNameOrPrice(String name, double price);

	// 5. Find products by name and price (AND condition)
	List<Product> findByNameAndPrice(String name, double price);

	// ========== CUSTOM QUERIES WITH @QUERY ==========

	// 1. Basic JPQL query with named parameter
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	List<Product> getProductByName(@Param("name") String name);

	// 2. Using indexed parameters (?1, ?2)
	@Query("SELECT p FROM Product p WHERE p.price > ?1")
	List<Product> getProductsByPriceGreaterThan(double price);

	// 3. Average price of all products
	@Query("SELECT AVG(p.price) FROM Product p")
	Double getAveragePriceOfAllProducts();

	// 4. Sum of all product prices
	@Query("SELECT SUM(p.price) FROM Product p")
	Double getTotalPriceSum();

}
