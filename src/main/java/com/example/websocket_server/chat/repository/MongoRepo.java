package com.example.websocket_server.chat.repository;

import com.example.websocket_server.chat.entity.Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoRepo extends MongoRepository<Mongo, String> {

    List<Mongo> findBySender(String sender);

    List<Mongo> findByChatId(String roomId);
}
