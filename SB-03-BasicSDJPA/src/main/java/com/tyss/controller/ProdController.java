package com.tyss.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.entity.Product;
import com.tyss.repo.ProdRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProdController {

    @Autowired
    private ProdRepo repo;

 // CREATE - Add new product
    @PostMapping
    public Product add(@RequestBody Product p) {
        return repo.save(p);
    }
    
 // CREATE MULTIPLE - saveAll()
    @PostMapping("/bulk")
    public List<Product> addMultiple(@RequestBody List<Product> products) {
        return repo.saveAll(products);
    }

 // READ ALL - Get all products
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }
    
    // READ ONE - Get product by ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer  id) {
        return repo.findById(id).orElse(null);
    }
    
 // UPDATE - Update existing product
    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product p) {
        // Check if product exists
        Optional<Product> existing = repo.findById(id);
        if (existing.isPresent()) {
            p.setId(id);  // Set the ID to ensure update instead of insert
            return repo.save(p);
        }
        return null;  // Return null if not found
    }
    
 // DELETE - Delete product by ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        repo.deleteById(id);
        return "Product deleted with id: " + id;
    }
    
 // DELETE - Delete product by object (alternative)
    @DeleteMapping
    public String deleteProduct(@RequestBody Product p) {
        repo.delete(p);
        return "Product deleted";
    }
}
