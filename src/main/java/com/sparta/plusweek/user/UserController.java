package com.sparta.plusweek.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // signup
    @PostMapping("/signup")
    public String signup(@RequestBody UserRequestDto userRequestDto) {
        userService.signup(userRequestDto);
        return "User registration completed";
    }
}