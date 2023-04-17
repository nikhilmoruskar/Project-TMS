package com.app.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("spring.mail.username")
    private String fromEmail;

    public void sendSimpleEmail(String toEmail
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("travelbuddy2223@gmail.com");
        message.setTo(toEmail);
        String body="Dear "+toEmail+",\n\n"
        		+ "Thank you for booking your ticket with TravelBuddy.\n"
        		+ "Ticket details:\n"
        		+ "Ticket number (TIN): TR5797413536\n\n"+
        		"\nBest,\n"
        		+ "Team TravelBuddy";
        message.setText(body);
        message.setSubject("TravelBuddy - Tax Invoice");
        mailSender.send(message);
        System.out.println("Mail Send...");


    }

}