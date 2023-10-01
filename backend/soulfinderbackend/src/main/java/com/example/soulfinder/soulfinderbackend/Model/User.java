package com.example.soulfinder.soulfinderbackend.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    
    @Id
    private String userId;
    private String firebaseUID;
    private String email;
    private String name;
    private String dpUrl;
    private List<String> postIds;

}
