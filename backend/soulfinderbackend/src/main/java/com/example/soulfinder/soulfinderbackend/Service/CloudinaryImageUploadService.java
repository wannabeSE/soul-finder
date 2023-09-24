package com.example.soulfinder.soulfinderbackend.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;


@Service
public class CloudinaryImageUploadService  {

    @Autowired
    private Cloudinary cloudinary;

    public String upload(MultipartFile file){
        try {
            var res = cloudinary.uploader().upload(file.getBytes(), Map.of());
            return (String) res.get("url");

        } catch (Exception e) {
            throw new RuntimeException( "Image Uploading Failed");
        }
    }
    
}
