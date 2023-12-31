package com.example.soulfinder.soulfinderbackend.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "Posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    
    @Id
    private String postId;
    private String body;
    private String userId;
    private String postType;
    private List<String> imageUrls;
    
    
    
}
