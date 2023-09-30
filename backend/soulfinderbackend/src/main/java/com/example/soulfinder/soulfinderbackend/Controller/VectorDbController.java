package com.example.soulfinder.soulfinderbackend.Controller;




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
@RequestMapping("api/vector-db")
public class VectorDbController {
    
  
    @Autowired
    private VectorDBService vDbService;
    
    @GetMapping("/db-status")
    public String dbActiveStatus(){
      
        if(vDbService.dbClassStatus() == "Error Occurred"){
            return "Error Occurred";
        }
        System.out.println("🔥 Server is up and listening to port: 8081 🔥");
        return vDbService.dbClassStatus();
        
    }

    @PostMapping("/create-db")
    public void createDB(){
        vDbService.schemaClassBuilder();
    }

    @PostMapping("/delete-db")
    public void deleteVectorDb(){
        vDbService.deleteVectorDBClass("Test");
    }

    @GetMapping("/db-check")
    public ResponseEntity<?> dbCheck(){
        Object response = vDbService.dbItemCounter();
        return ResponseEntity.status(HttpStatus.OK)
        .body(response);
    }

    @PostMapping("/get-image-by-id/{id}")
    public void getImageById(@PathVariable String id){
        vDbService.getImageByIdService(id);
    }

    @PostMapping("/delete-image-by-id/{id}")
    public void deleteImageById(@PathVariable String id){
        vDbService.deleteImageByIdService(id);
    }

    @PostMapping("/search")
    public ResponseEntity<?> imageSearch(@RequestParam("image") MultipartFile file){
        return ResponseEntity.status(HttpStatus.OK)
            .body(vDbService.imageSearchVectorDB(file));

    }

    
    
}
