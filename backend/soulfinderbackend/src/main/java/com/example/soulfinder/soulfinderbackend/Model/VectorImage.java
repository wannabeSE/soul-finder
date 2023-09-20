package com.example.soulfinder.soulfinderbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="Vector Images")
public class VectorImage{
    
    @Id
    private String imgId;
    private String vectorDbId;
    private String imgBase64;

    public VectorImage(String vectordbId, String img){
        this.vectorDbId = vectordbId;
        this.imgBase64 = img;
    }
}
