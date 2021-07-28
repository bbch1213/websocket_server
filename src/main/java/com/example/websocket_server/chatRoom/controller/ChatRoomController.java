package com.example.websocket_server.chatRoom.controller;

import com.example.websocket_server.chatRoom.entity.ChatRoom;
import com.example.websocket_server.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/room")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping(value = "/list")
    public Page<ChatRoom> getList(Pageable pageable)
    {
        return chatRoomService.getAllList(pageable);
    }

    @PostMapping(value = "/create")
    public String createRoom(@RequestBody ChatRoom chatRoom)
    {
        String retVal = "100";
        try{
            chatRoomService.createRoom(chatRoom);
        }catch (Exception e)
        {
            return retVal;
        }
        retVal = "200";
        return retVal;
    }
}
