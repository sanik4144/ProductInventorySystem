package com.anik.productInventorySystem.Services;

import com.anik.productInventorySystem.DTO.ProductRequest;
import com.anik.productInventorySystem.Entities.Product;
import com.anik.productInventorySystem.Exceptions.ProductNotFoundException;
import com.anik.productInventorySystem.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public String addProduct(ProductRequest productRequest){
        Product product = new Product();

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        productRepo.save(product);

        return "Product Added Successfully";
    }

    public List<Product> showAllProducts(){
        return productRepo.findAll();
    }

    public Product showProductById(Long id){
        return productRepo.findById(id).orElseThrow(
                ()-> new ProductNotFoundException(id)
        );
    }

    public String deleteProduct(Long id){
        Product productToDelete = showProductById(id);

        productRepo.delete(productToDelete);
        return "Product Deleted";
    }

    public String updateProduct(Long id, ProductRequest updatedProduct){
        Product productToUpdate = showProductById(id);

        productToUpdate.setName(updatedProduct.getName());
        productToUpdate.setDescription(updatedProduct.getDescription());
        productToUpdate.setPrice(updatedProduct.getPrice());
        productToUpdate.setQuantity(updatedProduct.getQuantity());

        productRepo.save(productToUpdate);
        return "Product Updated";
    }
}
