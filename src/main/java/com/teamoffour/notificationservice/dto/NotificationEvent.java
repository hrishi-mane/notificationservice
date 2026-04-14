package com.teamoffour.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEvent {
    private Long memberId;
    private String emailId;
    private String message;
    private String type; // e.g. "BORROWED", "RETURNED"
}

