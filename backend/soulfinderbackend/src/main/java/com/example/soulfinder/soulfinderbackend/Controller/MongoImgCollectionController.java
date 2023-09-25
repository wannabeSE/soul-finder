package com.example.soulfinder.soulfinderbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soulfinder.soulfinderbackend.Model.VectorImage;
import com.example.soulfinder.soulfinderbackend.Service.VectorDBService;


@RestController
@RequestMapping("api/mongo")
public class MongoImgCollectionController {

    @Autowired
    private VectorDBService vectorDBService;
    

    @GetMapping("/get-all-images")
    public ResponseEntity<List<VectorImage>> getAllVectorImagesFromMongo(){
        return ResponseEntity.status(HttpStatus.OK)
        .body(vectorDBService.getAllVectorImgFromMongoService());
    }
}
