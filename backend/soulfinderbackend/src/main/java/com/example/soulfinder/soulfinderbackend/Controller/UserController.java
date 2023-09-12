package com.example.soulfinder.soulfinderbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user/")

public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public Object createUser(@RequestBody User user){
        return userService.userCreationService(user);
    }

    @GetMapping(value="/get-users")
    public Object getAllUsers() {
        return userService.getAllUsersService();
    }
    
}
