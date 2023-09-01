package com.example.soulfinder.soulfinderbackend.Controller;


import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.WeaviateSchema;
import com.example.soulfinder.soulfinderbackend.Service.VectorDBService;

import io.weaviate.client.WeaviateClient;
import io.weaviate.client.base.Result;



import io.weaviate.client.v1.schema.model.Schema;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class WeaviateController {
    
  
    WeaviateClient client = WeaviateSchema.retConfig();
    @GetMapping("/")
    public String dbActiveStatus(){
      
        WeaviateClient client = WeaviateSchema.retConfig();
        System.out.println("🔥 Server is up and listening to port: 8081 🔥");
        Result <Schema> dbResult = client.schema().getter().run();
        if(dbResult.hasErrors()){
            return "Error occured";
        }
        return dbResult.getResult().toString();
    }
    

    @PostMapping(value="/file-upload")
    public ResponseEntity<?> fileUploader(@RequestParam("image") MultipartFile file) throws IOException {
        String response = VectorDBService.vectorDbImageUploader(file);
        
        if(response == "Ok"){
            return ResponseEntity.status(HttpStatus.OK)
        .body ("Uploaded Successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body ("Uploading failed");
        }
    }

    @GetMapping("/dbcheck")
    public ResponseEntity<?> dbCheck(){
        Object response = VectorDBService.dbItemCounter();
        return ResponseEntity.status(HttpStatus.OK)
        .body(response);
    }
<<<<<<< HEAD
    @PostMapping("/search")
    public void imageSearch(@RequestParam("image") MultipartFile file){
        try {
            VectorDBService.imageSearchVectorDB(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //VectorDBService.imageSearchVectorDB(file);
    }
=======
>>>>>>> f0b500d89890cf7738fd4748697bce4826aeb6a3
    
}
