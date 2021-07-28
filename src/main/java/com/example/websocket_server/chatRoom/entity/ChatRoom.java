package com.example.websocket_server.chatRoom.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    private String title;
    private String userId;

    @Column(columnDefinition = "default 1")
    private int participantCount;

    @CreationTimestamp
    private LocalDateTime regDt;

}
