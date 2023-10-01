package com.example.soulfinder.soulfinderbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/api/user/")

public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user ){
        return userService.userCreationService(user);
    }

    @GetMapping(value="/get-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(userService.getAllUsersService());
    }

    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK)
        .body(userService.getUserByIdService(userId));
    }

    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<User> getUserEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK)
        .body(userService.getUserEmailIdService(email));
    }
}
