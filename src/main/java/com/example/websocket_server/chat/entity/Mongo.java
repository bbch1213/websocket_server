package com.example.websocket_server.chat.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "mongotest")
@Getter
@Setter
public class Mongo {

    @Id
    private String _id;

    private String chatId;

    private String sender;

    private String message;
}
