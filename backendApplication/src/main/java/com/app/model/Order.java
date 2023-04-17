package com.app.model;

import java.util.Arrays;

import com.app.pojo.OrderItems;
import com.app.pojo.CustomerAddress;

public class Order {
	private OrderItems[] orderItems;
	private CustomerAddress shippingAddress;
	private int user;
	private String source;
	private String destination;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(OrderItems[] orderItems, CustomerAddress shippingAddress, int user) {
		super();
		this.orderItems = orderItems;
		this.shippingAddress = shippingAddress;
		this.user = user;
	}

	public OrderItems[] getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItems[] orderItems) {
		this.orderItems = orderItems;
	}

	public CustomerAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(CustomerAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderItems=" + Arrays.toString(orderItems) + ", shippingAddress=" + shippingAddress + ", user="
				+ user + "]";
	}

}
