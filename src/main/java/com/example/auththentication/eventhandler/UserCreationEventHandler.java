package com.example.auththentication.eventhandler;

import com.example.auththentication.dto.event.UserCreationEvent;
import com.example.auththentication.model.User;
import com.example.auththentication.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreationEventHandler {
    private final MailService mailService;


    @Async
    @EventListener
    public void handleUserCreationEvent (UserCreationEvent event) {
        User user = event.getUser();
        mailService.sendUserCreationMail(user);
    }
}
