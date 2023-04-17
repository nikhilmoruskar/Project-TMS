package com.app.service;



import com.app.pojo.CustomerAddress;

public interface IAddressService {

	CustomerAddress saveShippingAddress(CustomerAddress shippingAddress);
}
