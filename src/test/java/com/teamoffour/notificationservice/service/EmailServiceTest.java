package com.teamoffour.notificationservice.service;

import com.teamoffour.notificationservice.dto.NotificationEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmailOnBorrowEvent() {
        NotificationEvent event = new NotificationEvent(
                1L,
                "manehrishikesh229@gmail.com",
                "Book borrowed successfully. Due date: 2026-05-01",
                "BORROWED"
        );

        // Should not throw any exception
        assertDoesNotThrow(() -> emailService.send(event));
    }

    @Test
    void handleNullEmailGracefully() {
        NotificationEvent event = new NotificationEvent(
                1L,
                null,
                "Test message",
                "BORROWED"
        );

        assertDoesNotThrow(() -> emailService.send(event));
    }
}