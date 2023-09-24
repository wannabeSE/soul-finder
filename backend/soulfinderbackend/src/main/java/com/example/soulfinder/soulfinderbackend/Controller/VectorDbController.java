package com.example.soulfinder.soulfinderbackend.Controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Service.VectorDBService;



@CrossOrigin
@RestController
@RequestMapping("/vectordb")
public class VectorDbController {
    
  
    @Autowired
    private VectorDBService vDbService;
    
    @GetMapping("/dbstatus")
    public String dbActiveStatus(){
      
        if(vDbService.dbClassStatus() == "Error Occurred"){
            return "Error Occurred";
        }
        System.out.println("🔥 Server is up and listening to port: 8081 🔥");
        return vDbService.dbClassStatus();
        
    }

    @PostMapping("/createdb")
    public void createDB(){
        vDbService.schemaClassBuilder();
    }

    @PostMapping("/deletedb")
    public void deleteVectorDb(){
        vDbService.deleteVectorDBClass("Test");
    }

    @PostMapping(value="/file-upload")
    public ResponseEntity<?> fileUploader(@RequestParam("images") MultipartFile[] files) throws IOException {
        String response = "";

        for (MultipartFile file : files) {
            response = vDbService.vectorDbImageUploader(file);
        }
        
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
        Object response = vDbService.dbItemCounter();
        return ResponseEntity.status(HttpStatus.OK)
        .body(response);
    }

    @PostMapping("/get-image-byid/{id}")
    public void getImageById(@PathVariable String id){
        vDbService.getImageByIdService(id);
    }

    @PostMapping("/delete-image-byid/{id}")
    public void deleteImageById(@PathVariable String id){
        vDbService.deleteImageByIdService(id);
    }

    @PostMapping("/search")
    public void imageSearch(@RequestParam("image") MultipartFile file){
        try {
            vDbService.imageSearchVectorDB(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/get-all-images")
    public ResponseEntity<Object> getAllVectorImagesFromMongo(){
        return ResponseEntity.status(HttpStatus.OK)
        .body(vDbService.getAllVectorImgFromMongoService());
    }
    
}
