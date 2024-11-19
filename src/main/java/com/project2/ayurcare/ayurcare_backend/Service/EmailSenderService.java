//package com.project2.ayurcare.ayurcare_backend.Service;
//
//import com.project2.ayurcare.ayurcare_backend.entity.listener.Email;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailSenderService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public Boolean sendEmail(Email email) {
//        try {
//
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
//
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("kumarasinghesithija@gmail.com");
//            message.setTo(email.getToEmail());
//            message.setSubject(email.getSubject());
//            messageHelper.setText(email.getBody(), true);
//
//            mailSender.send(message);
//            System.out.println("mail sent successfully");
//            return true;
//        }catch (Exception e){
//            System.err.println("Error sending email: " + e.getMessage());
//            return false;
//        }
//    }
//}


package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.entity.listener.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public Boolean sendEmail(Email email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            messageHelper.setFrom("kumarasinghesithija@gmail.com");
            messageHelper.setTo(email.getToEmail());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getBody(), true); // Set to true to indicate HTML content

            mailSender.send(mimeMessage);
            System.out.println("mail sent successfully");
            return true;
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            return false;
        }
    }
}
