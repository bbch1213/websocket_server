package com.example.websocket_server.user.controller;

import com.example.websocket_server.user.entity.LoginValue;
import com.example.websocket_server.user.entity.User;
import com.example.websocket_server.user.entity.UserInfo;
import com.example.websocket_server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public String signup(@RequestBody UserInfo userInfo)
    {
        try{
            System.out.println(userInfo.getPassword());
            userService.save(userInfo);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "100";
        }
        return "200";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserInfo userInfo)
    {
        if(userService.checkUser(userInfo).equals(LoginValue.ID))
            return "존재하지 않는 아이디입니다.";
        else if(userService.checkUser(userInfo).equals(LoginValue.PASS))
            return "잘못된 비밀번호입니다.";
        else
            return "200";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        try{
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        }catch (Exception e)
        {
            return "100";
        }
        return "200";
    }
}
