package com.eblog.demo.service;

import jakarta.mail.internet.MimeMessage;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  public boolean sendEmail(String subject,String message, String to){
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      messageHelper.setFrom(sender);
      messageHelper.setTo(to);
      messageHelper.setSubject(subject);
      messageHelper.setText(message, true);
      javaMailSender.send(mimeMessage);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }
  public int generateOTP(int otpLength) {
    StringBuilder otp = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < otpLength; i++) {
      otp.append(random.nextInt(10));
    }
    return Integer.parseInt(otp.toString());
  }
}
