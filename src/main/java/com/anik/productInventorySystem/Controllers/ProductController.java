package com.anik.productInventorySystem.Controllers;

import com.anik.productInventorySystem.DTO.ProductRequest;
import com.anik.productInventorySystem.Entities.Product;
import com.anik.productInventorySystem.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<String> addProducts(@RequestBody ProductRequest productRequest){
        String product = productService.addProduct(productRequest);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public List<Product> showAllProducts(){
        return productService.showAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product showProductById(@PathVariable("id") Long id){
        return productService.showProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProducts(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
