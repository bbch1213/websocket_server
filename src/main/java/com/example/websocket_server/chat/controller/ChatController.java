package com.example.websocket_server.chat.controller;

import com.example.websocket_server.chat.entity.Chat;
import com.example.websocket_server.chat.entity.Mongo;
import com.example.websocket_server.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/receive/{id}")
    @SendTo("/send/{id}")
    public Chat message(Chat chat) throws Exception {
        return chatService.messageHandle(chat);
    }

    @GetMapping(value = "/chat/findBySender/{sender}")
    public List<Mongo> findBySender(@PathVariable String sender)
    {
        return chatService.findBySender(sender);
    }

    @GetMapping(value = "/chat/findByRoomId/{roomId}")
    public List<Mongo> findByRoomId(@PathVariable String roomId)
    {
        return chatService.findByRoomId(roomId);
    }
}
