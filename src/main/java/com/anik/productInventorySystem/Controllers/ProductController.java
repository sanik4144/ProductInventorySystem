package com.anik.productInventorySystem.Controllers;

import com.anik.productInventorySystem.DTO.ProductRequest;
import com.anik.productInventorySystem.Entities.Product;
import com.anik.productInventorySystem.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<String> addProducts(@RequestBody ProductRequest productRequest) {
        String product = productService.addProduct(productRequest);
        return ResponseEntity.ok(product);
    }

    @PreAuthorize("hasRole('VIEWER') or hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/products")
    public List<Product> showAllProducts() {
        return productService.showAllProducts();
    }

    @PreAuthorize("hasRole('VIEWER') or hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/products/{id}")
    public Product showProductById(@PathVariable("id") Long id) {
        return productService.showProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public String deleteProducts(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id,
            @RequestBody ProductRequest productToUpdate) {
        String updatedProduct = productService.updateProduct(id, productToUpdate);
        return ResponseEntity.ok(updatedProduct);
    }
}
