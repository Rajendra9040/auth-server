package com.example.auththentication.utils;

import com.example.auththentication.dto.event.ApplicationEvent;
import lombok.NonNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher implements ApplicationEventPublisherAware {
    private static ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher publisher) {
        eventPublisher = publisher;
    }

    public static <T extends ApplicationEvent> void publishEvent(T event) {
        if (eventPublisher != null) {
            eventPublisher.publishEvent(event);
        } else {
            throw new IllegalStateException("EventPublisher has not been initialized.");
        }
    }
}
