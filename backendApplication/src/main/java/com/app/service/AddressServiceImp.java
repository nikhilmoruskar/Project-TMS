package com.app.service;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.IAddressDao;
import com.app.pojo.CustomerAddress;

@Service
@Transactional
public class AddressServiceImp implements IAddressService {

	@Autowired
	private IAddressDao dao;
	
	@Override
	public CustomerAddress saveShippingAddress(CustomerAddress shippingAddress) {
		
		return dao.save(shippingAddress);
	}

}
