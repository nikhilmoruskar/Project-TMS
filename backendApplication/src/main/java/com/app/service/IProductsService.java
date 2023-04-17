package com.app.service;

import java.util.List;

import com.app.pojo.Products;
import com.app.pojo.User;

public interface IProductsService {
    List<Products> getAllProducts();
    
    Products saveProductsDetails(Products transientProducts);
    
    Products getProductsDetailsById(int productId);
    
    String deleteProduct(int userId);
    
    Products updateProductDetails(Products detachedProduct);
    
    
    
    
    
    
}
