package com.example.soulfinder.soulfinderbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Service.UserService;



@RestController
@RequestMapping("/api/user/")

public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public Object createUser(@RequestBody User user){
        return userService.userCreationService(user);
    }

    @GetMapping(value="/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(userService.getAllUsersService());
    }

    @GetMapping("/get-all-users/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId ){
        return ResponseEntity.status(HttpStatus.OK)
        .body(userService.getUserByIdService(userId));
    }
}
