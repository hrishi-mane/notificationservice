package com.teamoffour.notificationservice.contract;


import com.teamoffour.notificationservice.controller.NotificationController;
import com.teamoffour.notificationservice.dto.NotificationEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NotificationContractTest {

    @Autowired
    private NotificationController notificationController;

    @Test
    void acceptsValidNotificationEvent() {
        NotificationEvent event = new NotificationEvent(
                1L,
                "test@gmail.com",
                "Book borrowed successfully",
                "BORROWED"
        );

        ResponseEntity<String> response = notificationController.notify(event);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void handlesAllNotificationTypes() {
        List<String> types = List.of(
                "BORROWED", "RETURNED", "OVERDUE",
                "RESERVATION_CREATED", "RESERVATION_AVAILABLE"
        );

        types.forEach(type -> {
            NotificationEvent event = new NotificationEvent(
                    1L, "test@gmail.com", "Test message", type);
            assertDoesNotThrow(() -> notificationController.notify(event));
        });
    }
}
