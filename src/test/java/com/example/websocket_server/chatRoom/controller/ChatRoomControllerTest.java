package com.example.websocket_server.chatRoom.controller;

import com.example.websocket_server.chatRoom.entity.ChatRoom;
import com.example.websocket_server.chatRoom.service.ChatRoomService;
import com.example.websocket_server.test.SuperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
@Transactional
@Ignore
public class ChatRoomControllerTest extends SuperTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void get_테스트() throws Exception {
        this.mock.perform(get("/room/list")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void post_테스트() throws Exception {
        ChatRoom chatRoom = ChatRoom.builder()
                .chatId(100L)
                .title("test")
                .userId("tester")
                .build();
        this.mock.perform(post("/room/create")
                .content(objectMapper.writeValueAsString(chatRoom))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}