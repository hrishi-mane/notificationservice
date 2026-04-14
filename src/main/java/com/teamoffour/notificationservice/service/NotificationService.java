package com.teamoffour.notificationservice.service;


import com.teamoffour.notificationservice.dto.NotificationEvent;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailService emailService;


    public NotificationService(EmailService emailService) {
        this.emailService = emailService;

    }

    public void handle(NotificationEvent event) {
        emailService.send(event);

        // can add more channels here independent of notification service
    }
}
