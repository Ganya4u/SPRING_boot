package com.tyss.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.pojo.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	// 1️⃣ Query Parameter
    @GetMapping("/query")
    public String getByQuery(@RequestParam int id,
                             @RequestParam String name) {

        return "Query Param -> ID: " + id + " Name: " + name;
    }


    // 2️⃣ Path Variable
    @GetMapping("/path/{id}")
    public String getByPath(@PathVariable int id) {

        return "Path Variable -> Product ID: " + id;
    }


    // 3️⃣ Request Body (JSON)
    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {

        System.out.println(product.getName());
        return product;
    }


    // 4️⃣ Request Header
    @PutMapping("/update")
    public String updateProduct(@RequestHeader String token,
                                @RequestHeader String brand) {

        return "Header Received -> Token: " + token + " Brand: " + brand;
    }


    // 5️⃣ Delete using Path Variables
    @DeleteMapping("/delete/{id}/{name}")
    public String deleteProduct(@PathVariable int id,
                                @PathVariable String name) {

        return "Deleted Product -> ID: " + id + " Name: " + name;
    }

	

}
