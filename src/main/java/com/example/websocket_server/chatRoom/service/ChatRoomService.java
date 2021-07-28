package com.example.websocket_server.chatRoom.service;

import com.example.websocket_server.chatRoom.entity.ChatRoom;
import com.example.websocket_server.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional(readOnly = true)
    public Page<ChatRoom> getAllList(Pageable pageable)
    {
        return chatRoomRepository.findAllByOrderByRegDtDesc(pageable);
    }

    @Transactional(readOnly = true)
    public ChatRoom get(long id) throws Exception {
        return chatRoomRepository.findById(id).orElseThrow(Exception::new);
    }

    public void createRoom(ChatRoom chatRoom)
    {
        chatRoomRepository.save(chatRoom);
    }

    public void increaseCount(long chatId) throws Exception {
        ChatRoom chatRoom = get(chatId);
        chatRoom.setParticipantCount(chatRoom.getParticipantCount()+1);
        chatRoomRepository.save(chatRoom);
    }

    public void decreaseCount(long chatId) throws Exception {
        ChatRoom chatRoom = get(chatId);
        chatRoom.setParticipantCount(chatRoom.getParticipantCount()-1);
        chatRoomRepository.save(chatRoom);
    }
}
