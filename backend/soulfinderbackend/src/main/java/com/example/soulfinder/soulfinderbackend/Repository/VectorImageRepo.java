package com.example.soulfinder.soulfinderbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.soulfinder.soulfinderbackend.Model.VectorImage;

public interface VectorImageRepo extends MongoRepository<VectorImage, String>{
    
}
