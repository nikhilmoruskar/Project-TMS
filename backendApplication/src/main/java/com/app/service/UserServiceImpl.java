package com.app.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.IOrderItemsDao;
import com.app.dao.IUserDao;
import com.app.pojo.OrderItems;
import com.app.pojo.User;
import com.app.security.CryptWithMD5;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IOrderItemsDao orderDao;

	@Override
	public Optional<User> signIn(User user) {
		// TODO Auto-generated method stub
		String password = CryptWithMD5.cryptWithMD5(user.getPassword());
//		return userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
		return userDao.findByEmailAndPassword(user.getEmail(), password);
	}

//	@Override
//	public User forgetPassword(User user) {
//		// TODO Auto-generated method stub
//		return userDao.findByMobileNumber(user.getMobileNumber());
//	}
//
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public User saveUserDetails(User transientUser) {
		// TODO Auto-generated method stub
		return userDao.save(transientUser);
	}
	
	
	@Override
	public User restPass(User u,String password) {
		// TODO Auto-generated method stub
		
		System.out.println("inside userservice");
		System.out.println(u);
		u.setPassword(password);
		User persistentUser=userDao.save(u);
		return persistentUser;
	}

	@Override
	public User checkEmail(String email) {
		User validuser=userDao.findByEmail(email);
		return validuser;
	}

	@Override
	public Optional<User> getUserDetailsById(int productId) {
		// TODO Auto-generated method stub
		return userDao.findById(productId);
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public User updateProfile(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		userDao.deleteById(userId);
		return "Product Details Deleted";
	}

	@Override
	public User updateUserDetails(User detachedUser) {
		// TODO Auto-generated method stub
		return userDao.save(detachedUser);
	}

	@Override
	public List<OrderItems> getAllorders(int userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user.set_id(userId);
		return orderDao.findByUser(user) ;
	}

		 
}
