package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojo.OrderItems;
import com.app.pojo.User;

public interface IUserService{
	
	User saveUserDetails(User transientUser);
	
//	Java introduced a new class Optional in jdk8.
//	It is a public final class and used to deal with NullPointerException in Java application.  java.util package
//. It can help in writing a neat code without using too many null checks. By using Optional, we can specify alternate values to return or alternate code to run. 
	//This makes the code more readable because the facts which were hidden are now visible to the developer.
	Optional<User> signIn(User user);
	
	User checkEmail(String email);
	
	User restPass(User u,String password);

	//User forgetPassword(User user);
	
	User save(User user);
	
	Optional<User> getUserDetailsById(int productId);
	

	List<User> getAllUser();
	
    User updateProfile(User user);
	
	String deleteUser(int userId);
	
	User updateUserDetails(User detachedUser);
	
	List<OrderItems> getAllorders(int userId);
}
