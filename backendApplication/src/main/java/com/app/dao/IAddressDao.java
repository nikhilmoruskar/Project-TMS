package com.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojo.CustomerAddress;

public interface IAddressDao extends JpaRepository<CustomerAddress, Integer> {

}
