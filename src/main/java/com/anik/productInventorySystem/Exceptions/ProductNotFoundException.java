package com.anik.productInventorySystem.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("No product found with ID: "+id);
    }
}
