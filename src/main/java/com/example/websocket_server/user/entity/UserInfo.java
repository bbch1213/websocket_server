package com.example.websocket_server.user.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

    private String email;
    private String password;
    private String auth;
}
