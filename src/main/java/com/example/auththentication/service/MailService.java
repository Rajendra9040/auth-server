package com.example.auththentication.service;

import com.example.auththentication.model.User;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender emailSender;

    @SneakyThrows
    public void sendMail(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("rajumahapatra096@gmail.com", "Registration-service");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        emailSender.send(message);
        System.out.println("Email Sent Successfully!!");
    }


    @SneakyThrows
    public void sendMail(String to, String subject, String text, List<String> filePaths) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("rajumahapatra096@gmail.com", "Registration-service");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                DataSource dataSource = new FileDataSource(file);
                helper.addAttachment(file.getName(), dataSource);
            } else {
                System.err.println("File not found: " + filePath);
            }
        }
        emailSender.send(message);
        System.out.println("Email Sent Successfully!!");
    }

    public void sendUserCreationMail(User user) {
        String subject = "User registered successfully";
        String body = String.format("Your user account with %s email have been created successfully!", user.getEmail());
        String attachedFilePath = "D:/PersonalDocuments/My_document/Aadhaar_card.pdf";
        List<String> filePaths = new ArrayList<>();
        filePaths.add(attachedFilePath);
        sendMail(user.getEmail(), subject, body, filePaths);
    }
}
