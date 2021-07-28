package com.example.websocket_server.chatRoom.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    @Test
    void setTitle() {
        final ChatRoom chatRoom = ChatRoom.builder()
                .build();
    }

    @Test
    void getTitle()
    {
        final ChatRoom chatRoom = ChatRoom.builder()
                .title("오옝")
                .build();
        final String title = chatRoom.getTitle();
        assertEquals("오옝", title);
    }

}