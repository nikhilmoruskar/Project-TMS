package com.app.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.Credential;
import com.app.dto.EmailDTO;
import com.app.dto.Response;
import com.app.dto.resultDto;
import com.app.pojo.User;
import com.app.security.CryptWithMD5;
import com.app.service.EmailService;
import com.app.service.IUserService;



@CrossOrigin(origins = "*")
@RestController

//@RequestMapping("/user/forgetPassword")
@RequestMapping("/forgetPassword")
public class ForgetController {
	Random random=new Random(1000);
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/sendotp")
	public resultDto Signup(@RequestBody EmailDTO req) {	
		
		
		resultDto resultdto=null;
		String result="";
		
		System.out.println("EMAIL"+req.getEmail());
		
		User validuser=userService.checkEmail(req.getEmail());
		if(validuser!=null) {
			System.out.println("yupp baby");
			
			int otp=random.nextInt(999999);
			String subject ="OTP from Travelmanagementsystem";
			String name=req.getName();
			String message="Hi,"+name+ "your Reset password OTP = "+otp+" ";
			String to=req.getEmail();
			
			
			boolean flag=this.emailservice.sendEmail(subject, message, to);		
			if(flag) {
				resultdto=new resultDto("success",otp);
			}else {
				resultdto=new resultDto("failure",otp);
			}
		}else{
			System.out.println("Ohhh sorrry ");
		}
				 
		return resultdto;
	}
	
	
	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody Credential cred){
		System.out.println(cred.toString());
		System.out.println("inside passwordreset");
		System.out.println(cred.getEmail());
		System.out.println(cred.getPassword());
		User validuser=userService.checkEmail(cred.getEmail());
		System.out.println(validuser);
		String password = CryptWithMD5.cryptWithMD5(cred.getPassword());
		User persistentUser=userService.restPass(validuser,password);
		if(persistentUser!=null) {
			System.out.println("successfully changed the password");
			return Response.success("Password changed sucessfully");
		}else {
			return Response.error("Password Not changed");
		}
		
	}
	
	
	
	

}
