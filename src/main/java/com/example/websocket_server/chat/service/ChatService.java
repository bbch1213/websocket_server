package com.example.websocket_server.chat.service;

import com.example.websocket_server.chat.entity.Chat;
import com.example.websocket_server.chat.entity.Mongo;
import com.example.websocket_server.chat.repository.MongoRepo;
import com.example.websocket_server.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final MongoRepo mongoRepo;
    private final ChatRoomService chatRoomService;

    public Chat messageHandle(Chat chat) throws Exception {
        Chat result = new Chat();

        if(chat.getType().equals(Chat.MessageType.ENTER))
        {
            long id = Long.parseLong(chat.getRoomId());
            result.setSender("시스템");
            result.setMessage(chat.getSender() + "님께서 채팅에 참여하였습니다.");

            chatRoomService.increaseCount(id);

            return result;
        }
        else if(chat.getType().equals(Chat.MessageType.QUIT))
        {
            long id = Long.parseLong(chat.getRoomId());
            result.setSender("시스템");
            result.setMessage(chat.getSender() + "님께서 채팅에서 나갔습니다.");

            chatRoomService.decreaseCount(id);

            return result;
        }
        else{
            String userName = chat.getSender();
            String content = chat.getMessage();

            result.setMessage(content);
            result.setSender(userName);

            Mongo mongo = new Mongo();
            mongo.setSender(chat.getSender());
            mongo.setMessage(chat.getMessage());
            mongo.setChatId(chat.getRoomId());
            try{
                mongoRepo.save(mongo);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return result;
        }
    }

    public List<Mongo> findBySender(String sender)
    {
        return mongoRepo.findBySender(sender);
    }

    public List<Mongo> findByRoomId(String roomId)
    {
        return mongoRepo.findByChatId(roomId);
    }
}
