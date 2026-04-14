package com.teamoffour.notificationservice.service;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import com.teamoffour.notificationservice.dto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailService {

    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from-email}")
    private String fromEmail;

    public void send(NotificationEvent event) {
        try {
            Email from = new Email(fromEmail, "Library Management System");
            Email to = new Email(event.getEmailId());
            String subject = buildSubject(event.getType());
            Content content = new Content("text/plain", event.getMessage());

            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            log.info("Email sent to {} | Status: {}", event.getEmailId(), response.getStatusCode());

        } catch (IOException e) {
            log.error("Email failed to {}: {}", event.getEmailId(), e.getMessage());
        }
    }

    private String buildSubject(String type) {
        return switch (type) {
            case "BORROWED" -> "Book Borrowed Successfully";
            case "RETURNED" -> "Book Returned";
            case "OVERDUE" -> "Overdue Notice - Fine Applied";
            case "RESERVATION_CREATED" -> "Reservation Confirmed";
            case "RESERVATION_AVAILABLE" -> "Your Reserved Book is Available!";
            default -> "Library Notification";
        };
    }
}