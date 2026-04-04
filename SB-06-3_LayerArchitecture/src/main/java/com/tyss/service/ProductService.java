package com.tyss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.dao.ProductDAO;
import com.tyss.entity.Product;

@Service
public class ProductService {

	
	

	    @Autowired
	    private ProductDAO dao;

	    public Product addProduct(Product product) {
	        return dao.save(product);
	    }
	    
	    public List<Product> saveAll(List<Product> products) {
	        return dao.saveAll(products);
	    }
	    
	    
	    // READ ALL
	    public List<Product> getAllProducts() {
	        return dao.findAll();
	    }
	    
	 // READ ONE
	    public Product getProductById(Integer id) {
	        Optional<Product> opt = dao.findById(id);
	        return opt.orElse(null);
	    }
	    
	    // UPDATE
	    public String updateProduct(Integer id, Product product) {
	        Optional<Product> opt = dao.findById(id);

	        if (opt.isPresent()) {
	            Product dbProduct = opt.get();

	            dbProduct.setName(product.getName());
	            dbProduct.setPrice(product.getPrice());

	            dao.save(dbProduct);

	            return "Product updated successfully";
	        }

	        return "Product not found";
	    }

	    // DELETE
	    public String deleteProduct(Integer id) {
	        if (dao.existsById(id)) {
	            dao.deleteById(id);
	            return "Product deleted successfully";
	        }

	        return "Product not found";
	    }
}
