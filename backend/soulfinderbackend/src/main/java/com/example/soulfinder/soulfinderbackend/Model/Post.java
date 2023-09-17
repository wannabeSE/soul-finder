package com.example.soulfinder.soulfinderbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "Posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    
    @Id
    private String postId;
    private String body;
    
    public Post(String body){
        this.body = body;
    }
}
