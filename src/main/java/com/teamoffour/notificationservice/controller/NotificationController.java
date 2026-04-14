package com.teamoffour.notificationservice.controller;


import com.teamoffour.notificationservice.dto.NotificationEvent;
import com.teamoffour.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notify")
    public ResponseEntity<String> notify(@RequestBody NotificationEvent event) {
        notificationService.handle(event);
        return ResponseEntity.ok("Notification dispatched");
    }
}