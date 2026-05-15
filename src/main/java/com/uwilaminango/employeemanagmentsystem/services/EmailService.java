package com.uwilaminango.employeemanagmentsystem.services;

import com.uwilaminango.employeemanagmentsystem.models.Email;
import com.uwilaminango.employeemanagmentsystem.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailRepository emailRepository;

    public void sendEmail(String to, String subject, String body){
        Email e = new Email();

        e.setRecipient(to);
        e.setSubject(subject);
        e.setMessage(body);
        e.setSentAt(LocalDateTime.now());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            e.setStatus("SUCCESS");
        }catch(Exception ex){
            e.setStatus("FAILURE");
            System.out.println(ex.getMessage());
        }
        emailRepository.save(e);
    }

    public void sendResetLink(String toEmail, String resetLink){
        String subject="Password ResetLink";

        String body="Hello\n\nClick the reset link below to reset your password:\n"+resetLink+"\n\n If you didn't request this, please ignore this email.";
    }
}
