package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojo.OrderItems;
import com.app.pojo.User;

public interface IOrderItemsDao extends JpaRepository<OrderItems, Integer> {

	List<OrderItems> findByUser(User user);
}
