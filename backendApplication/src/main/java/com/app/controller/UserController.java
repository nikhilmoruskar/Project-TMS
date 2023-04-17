package com.app.controller;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IOrderItemsDao;
import com.app.dto.ErrorResponse;
import com.app.email.EmailSenderService;
import com.app.model.Order;
import com.app.pojo.CustomerAddress;
import com.app.pojo.OrderItems;
import com.app.pojo.User;
import com.app.security.CryptWithMD5;
import com.app.service.IAddressService;
import com.app.service.IUserService;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	  private IUserService userService;
	  
	
	@Autowired 
	private IAddressService addressService;
	
	@Autowired 
	private IOrderItemsDao orderDao;
	
	@Autowired 
	private EmailSenderService emailService;
	
	
	@GetMapping("/orders/{id}")
	  public ResponseEntity<?> getAllOrders(@PathVariable int id)
	  {
	  	return new ResponseEntity<>(userService.getAllorders(id),HttpStatus.OK);
	  }
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user)
	{   
		String password = CryptWithMD5.cryptWithMD5(user.getPassword());
		user.setPassword(password);
		System.out.println("in save products " + user);
		return new ResponseEntity<>(userService.saveUserDetails(user), HttpStatus.CREATED);
	}
	
	@PostMapping ("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody User user)
	{
		Optional<User> validateuser=userService.signIn(user);
		if(validateuser.isEmpty())
		{
			ErrorResponse resp=new ErrorResponse("Enter Valid Email or Password", LocalDateTime.now());
			return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(validateuser.get(),HttpStatus.OK);
	}
	

	
	@GetMapping
	public List<User> fetchAllUser() {
		System.out.println("in get all users");
		return userService.getAllUser();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> fetchUserById(@PathVariable int id)
    {
    	return new ResponseEntity<>(userService.getUserDetailsById(id),HttpStatus.OK);
    }
	
	
	////Update User Details By User
	@PutMapping()
	public ResponseEntity<?> updateUserDetails(@RequestBody User user)
	{
    	fetchUserById(user.get_id());
		return new ResponseEntity<>(userService.updateProfile(user),HttpStatus.OK);
	}
	
	
	//Update User Details By Admin Using ID 
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserDetails(@RequestBody User detachedUser,@PathVariable int id)
	{
		System.out.println("in update "+detachedUser+" "+id);
		userService.getUserDetailsById(id);
		return new ResponseEntity<>(userService.saveUserDetails(detachedUser),HttpStatus.OK);
	}
	
	//admin
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUSerDetails(@PathVariable int userId)
	{
		System.out.println("in del user details"+userId);
		return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);
	}
	
//	@PostMapping("/forgetPassword")
//    public ResponseEntity<?> forgetUserPassword(@RequestBody User user) 
//    {
//			System.out.println(user);
//			try {
//				User validateuser=userService.forgetPassword(user);
//				validateuser.setPassword(user.getPassword());
//				userService.save(validateuser);
//				return new ResponseEntity<>(validateuser,HttpStatus.OK);
//			} catch (Exception e) {
//				ErrorResponse resp=new ErrorResponse("Enter Valid Mobile Number", LocalDateTime.now());
//				return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
//			}
//    }
	

	//	add
	
	 @PostMapping("/order")
	    public ResponseEntity<?> placeOrder(@RequestBody Order order) 
	    {
	    	try {
	    		OrderItems[] orderItems= order.getOrderItems();
	    		CustomerAddress shippingAddress=order.getShippingAddress();
	    		int userId=order.getUser();
	    		User user=userService.getUserDetailsById(userId).get();
	    		CustomerAddress persistAddress=addressService.saveShippingAddress(shippingAddress);
	    		user.setShippingAddress(persistAddress);
	    		userService.saveUserDetails(user);
	    		for(OrderItems o:orderItems) {
	    			o.setUser(user);
	    			orderDao.save(o);
	    			emailService.sendSimpleEmail(user.getEmail());
	    		}
	    		  		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
				
	    	return null;
	    }
	
}
