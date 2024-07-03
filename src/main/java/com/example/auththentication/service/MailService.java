package com.example.auththentication.service;

import com.example.auththentication.model.User;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final MessageSource messageSource;


    public void sendMail(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        helper.setFrom("rajumahapatra096@gmail.com", "Registration-service");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        emailSender.send(message);
        System.out.println("Email Sent Successfully!!");
    }



    public void sendMail(String to, String subject, String text, List<String> filePaths) throws MessagingException, UnsupportedEncodingException {
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
        String attachedFilePath = "D:/PersonalDocuments/My_document/Aadhaar_card.pdf";
        List<String> filePaths = new ArrayList<>();
        filePaths.add(attachedFilePath);
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("userName", user.getName());
        context.setVariable("baseUrl", "dummy-url");
        String htmlContent = templateEngine.process("user-registration", context);
        String subject = messageSource.getMessage("subject", null, locale);
        try {
            sendMail(user.getEmail(), subject, htmlContent, filePaths);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
