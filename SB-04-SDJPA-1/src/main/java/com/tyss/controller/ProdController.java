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

	// ========== BASIC CRUD (Existing) ==========

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

	// OPTIONAL USING

	// READ ONE - Get product by ID
	@GetMapping("/{id}")
	public Product getById(@PathVariable Integer id) {
		return repo.findById(id).orElse(null);
	}

	@GetMapping("/byId")
	public Product fetchById(@RequestBody Integer pid) {
		Optional<Product> opt = repo.findById(pid);

		if (opt.isPresent()) {
			Product product = opt.get();
			return product;
		} else {
			return null;
		}
	}

	// 4. Using orElseThrow() - Throw exception if empty
	@GetMapping("/orElseThrow/{id}")
	public Product getByIdOrElseThrow(@PathVariable Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));
	}

	// UPDATE - Update existing product
	@PutMapping("/{id}")
	public Product update(@PathVariable Integer id, @RequestBody Product p) {
		// Check if product exists
		Optional<Product> existing = repo.findById(id);
		if (existing.isPresent()) {
			p.setId(id); // Set the ID to ensure update instead of insert
			return repo.save(p);
		}
		return null; // Return null if not found
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

	@DeleteMapping("/all")
	public String deleteAll() {
		repo.deleteAll();
		return "All products deleted";
	}

	// ========== NEW ENDPOINTS USING CUSTOM METHODS ==========

	// 1. Find by exact name
	@GetMapping("/by-name")
	public List<Product> getByName(@RequestParam String name) {
		return repo.findByName(name);
	}

	// 2. Find products with price greater than
	@GetMapping("/price-greater-than")
	public List<Product> getByPriceGreaterThan(@RequestParam double price) {
		return repo.findByPriceGreaterThan(price);
	}

	// 3. Find products in price range
	@GetMapping("/price-range")
	public List<Product> getByPriceRange(@RequestParam double min, @RequestParam double max) {
		return repo.findByPriceBetween(min, max);
	}

	// 4. Find by name OR price
	@GetMapping("/by-name-or-price")
	public List<Product> getByNameOrPrice(@RequestParam String name, @RequestParam double price) {
		return repo.findByNameOrPrice(name, price);
	}

	// 5. Find by name AND price
	@GetMapping("/by-name-and-price")
	public List<Product> getByNameAndPrice(@RequestParam String name, @RequestParam double price) {
		return repo.findByNameAndPrice(name, price);
	}
	
	// ========== NEW ENDPOINTS USING CUSTOM QUERIES ==========
	
	// 1. Get product by name using @Query
    @GetMapping("/query/by-name")
    public List<Product> getProductByName(@RequestParam String name) {
        return repo.getProductByName(name);
    }
    
    // 2. Get products by price greater than
    @GetMapping("/query/price-greater-than")
    public List<Product> getProductsByPriceGreaterThan(@RequestParam double price) {
        return repo.getProductsByPriceGreaterThan(price);
    }
    
 // 3. Get average price
    @GetMapping("/query/average-price")
    public double getAveragePrice() {
        Double avg = repo.getAveragePriceOfAllProducts();
        return avg != null ? avg : 0.0;
    }
    
 // 4. Get total price sum
    @GetMapping("/query/total-price")
    public double getTotalPriceSum() {
        Double total = repo.getTotalPriceSum();
        return total != null ? total : 0.0;
    }

	// Content Type
    
    @GetMapping(
            value = "/content",
            consumes = {
                "application/json",
                "application/xml"
            },
            produces = {
                "application/json",
                "application/xml"
            }
        )
        public Product contentType(@RequestBody Product product) {

            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getPrice());

            return product;
        }
    
    
    
    @PostMapping(
            value = "/content2",
            consumes = {
                "application/json",
                "application/xml"
            },
            produces = {
                "application/json",
                "application/xml"
            }
            )
    public Product contentType2 (@RequestBody Product product) {

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());

        return product;
    }

	@GetMapping(value = "/content2/{id}", produces = {
            "application/json",
            "application/xml"
    })
    public Product getProduct(@PathVariable Integer id) {

        Product product = repo.findById(id).orElse(null);
        return product;
    }
	
    
	
	


}
