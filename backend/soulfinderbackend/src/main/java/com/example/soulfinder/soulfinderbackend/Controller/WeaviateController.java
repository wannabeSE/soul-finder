package com.example.soulfinder.soulfinderbackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.weaviate.client.Config;
import io.weaviate.client.WeaviateClient;
import io.weaviate.client.base.Result;
import io.weaviate.client.v1.schema.model.Schema;

@RestController
public class WeaviateController {
    
    @GetMapping("/")
    public String dbActiveStatus(){
        Config config = new Config("http", "localhost:8080");
        WeaviateClient client = new WeaviateClient(config);

        Result <Schema> dbResult = client.schema().getter().run();
        if(dbResult.hasErrors()){
            return "Error occured";
        }
        return dbResult.getResult().toString();
    }
}
