//package com.teamoffour.notificationservice.service;
//
//
//import com.teamoffour.notificationservice.dto.NotificationEvent;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class SmsService {
//
//    @Value("${twilio.phone-number}")
//    private String fromNumber;
//
//    public void send(NotificationEvent event) {
//        try {
//            Message message = Message.creator(
//                    new PhoneNumber(event.getPhoneNumber()),  // to
//                    new PhoneNumber(fromNumber),              // from
//                    buildSmsBody(event)
//            ).create();
//
//            log.info("SMS sent to {} | SID: {}", event.getPhoneNumber(), message.getSid());
//
//        } catch (Exception e) {
//            // Log and continue — SMS failure should not affect core service
//            log.error("SMS failed to {}: {}", event.getPhoneNumber(), e.getMessage());
//        }
//    }
//
//    private String buildSmsBody(NotificationEvent event) {
//        return String.format("[Library Notification - %s]\n%s", event.getType(), event.getMessage());
//    }
//}
