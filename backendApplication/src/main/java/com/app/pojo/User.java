package com.app.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_tbl")
public class User extends BaseEntity {
	   
	   private String name;
	   
	   @Column(unique=true)
	   @Email
	   private String email;
	   
	   private String password;
	   
	   @Column(name = "mobile_number")
	   private String mobileNumber;
	   
	   @Column(name = "is_Admin")
	   private Boolean isAdmin;

	   @OneToOne(cascade = CascadeType.ALL)
	   @JsonIgnoreProperties("address_id")
	   @JoinColumn(name = "address_id")	
	   private CustomerAddress shippingAddress;
	   
		   
	
	public User(String name, @Email String email, String password, String mobileNumber, Boolean isAdmin) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.isAdmin = isAdmin;
	}
	public User() {
		System.out.println("in user constr:"+getClass().getName());
	}
	
	public CustomerAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(CustomerAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Boolean getisAdmin() {
		return isAdmin;
	}
	public void setisAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber
				+ ", isAdmin=" + isAdmin + "]";
	}
	


}
