package com.example.soulfinder.soulfinderbackend.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.soulfinder.soulfinderbackend.Model.User;

public interface UserRepo extends MongoRepository<User, String> {


    
}
