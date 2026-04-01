package com.tyss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // ✅ CREATE - Add single product
    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product p) {
        Product saved = repo.save(p);
        
        System.out.println("Name: " + p.getName());
        System.out.println("Price: " + p.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ✅ CREATE MULTIPLE
    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> addMultiple(@RequestBody List<Product> products) {
        List<Product> saved = repo.saveAll(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ✅ READ ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> list = repo.findAll();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(list); // 200
    }

    // ✅ READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        Optional<Product> opt = repo.findById(id);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get()); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product p) {
        Optional<Product> existing = repo.findById(id);

        if (existing.isPresent()) {
            p.setId(id);
            Product updated = repo.save(p);
            return ResponseEntity.ok(updated); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // ✅ DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("Product deleted with id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Product not found");
        }
    }

    // ✅ DELETE ALL
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        repo.deleteAll();
        return ResponseEntity.ok("All products deleted");
    }
}