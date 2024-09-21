package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.models.Message;

@Controller // Add this annotation to mark the class as a WebSocket controller
public class RealTimeChat {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{groupId}")
    public Message sentToUser(@Payload Message message, @DestinationVariable String groupId) {
        // Send message to the user with the specified groupId and destination
        simpMessagingTemplate.convertAndSendToUser(groupId, "/private", message);
        return message;
    }
}
