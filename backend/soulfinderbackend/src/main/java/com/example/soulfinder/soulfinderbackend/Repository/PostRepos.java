package com.example.soulfinder.soulfinderbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.soulfinder.soulfinderbackend.Model.Post;

public interface PostRepos extends MongoRepository<Post, String>{
    
}
