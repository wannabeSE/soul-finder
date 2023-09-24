package com.example.soulfinder.soulfinderbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;



@Document(collection="Vector Images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VectorImage{
    
    @Id
    private String imgId;
    private String vectorDbId;
    private String imgUrl;
    
}
