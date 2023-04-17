package com.app.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojo.Products;


public interface IProductDao extends JpaRepository<Products, Integer> {
	
}
