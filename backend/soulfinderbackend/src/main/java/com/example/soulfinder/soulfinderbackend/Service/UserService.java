package com.example.soulfinder.soulfinderbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    public Object userCreationService(User user){
        Object createdUser = userRepo.save(user);
        return createdUser;
    }   

    public Object getAllUsersService(){
        return userRepo.findAll();
    }
}
