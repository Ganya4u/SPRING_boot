package com.tyss.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tyss.entity.Product;
import com.tyss.repo.ProdRepo;

@Repository
public class ProductDAO {
	
	 @Autowired
	    private ProdRepo repo;

	// CREATE + UPDATE
	    public Product save(Product product) {
	        return repo.save(product);
	    }
	    
	    public List<Product> saveAll(List<Product> products) {
	        return repo.saveAll(products);
	    }
	   
	 // READ ALL
	    public List<Product> findAll() {
	        return repo.findAll();
	    }
	    

	    // READ ONE
	    public Optional<Product> findById(Integer id) {
	        return repo.findById(id);
	    }

	    // DELETE
	    public void deleteById(Integer id) {
	        repo.deleteById(id);
	    }   
	    
	    public boolean existsById(Integer id) {
	        return repo.existsById(id);
	    }
	    

}
