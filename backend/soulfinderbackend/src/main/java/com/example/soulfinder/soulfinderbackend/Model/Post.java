package com.example.soulfinder.soulfinderbackend.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
    @DocumentReference
    private List<VectorImage> vectorImgIds;
    
    public Post(String body){
        this.body = body;
    }
}
