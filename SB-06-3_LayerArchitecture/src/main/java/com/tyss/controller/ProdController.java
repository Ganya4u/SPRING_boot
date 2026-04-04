package com.tyss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tyss.entity.Product;
import com.tyss.repo.ProdRepo;
import com.tyss.service.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProdController {

	@Autowired
    private ProductService service;

    // ✅ CREATE - Add single product
    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product p) {
        Product saved = service.addProduct(p);
        
        System.out.println("Name: " + p.getName());
        System.out.println("Price: " + p.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    // ✅ CREATE MULTIPLE
    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> addMultiple(@RequestBody List<Product> products) {
        List<Product> saved = service.saveAll(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
 // READ ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }
    
 // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        Product product = service.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        }

        return ResponseEntity.notFound().build();
    }
    
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,
                                         @RequestBody Product product) {
        return ResponseEntity.ok(service.updateProduct(id, product));
    }
    
    
 // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.deleteProduct(id));
    }
    
    
    

   
    
}