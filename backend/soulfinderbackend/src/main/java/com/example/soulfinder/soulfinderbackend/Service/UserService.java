package com.example.soulfinder.soulfinderbackend.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    public User userCreationService(User user){
        User createdUser = userRepo.save(user);
        return createdUser;
    }   

    public List<User> getAllUsersService(){
        List <User> userList = userRepo.findAll();
        return userList;
    }

    public Object getUserByIdService(String userObjectId){
        return userRepo.findById(userObjectId);
    }
}
