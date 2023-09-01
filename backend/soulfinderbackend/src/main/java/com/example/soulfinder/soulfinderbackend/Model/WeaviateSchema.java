package com.example.soulfinder.soulfinderbackend.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.weaviate.client.Config;
import io.weaviate.client.WeaviateClient;


import io.weaviate.client.base.Result;
import io.weaviate.client.v1.schema.model.WeaviateClass;

public class WeaviateSchema {

    public static WeaviateClient retConfig(){
        Config config = new Config("http","localhost:8080");
        WeaviateClient client = new WeaviateClient(config);
        return client;
    }
<<<<<<< HEAD
    public void schemaDesign(){
=======
    public static void schemaDesign(){
>>>>>>> f0b500d89890cf7738fd4748697bce4826aeb6a3
        Map<String, Object> img2vec = new HashMap<>();
        HashMap <String, Object> img2vecNeural = new HashMap<>();
        ArrayList <String> imageFields = new ArrayList<>();
        imageFields.add("image");
        img2vecNeural.put("imageFields", imageFields);
        img2vec.put("img2vec-neural", img2vecNeural);
        WeaviateClass clazz = WeaviateClass.builder()
        .className("Test")
        .vectorizer("img2vec-neural")
        .vectorIndexType("hnsw")
        .moduleConfig(img2vec)
        .properties(new ArrayList<>(){{
            add(io.weaviate.client.v1.schema.model.Property.builder()
            .dataType(new ArrayList<>(){{
                add("blob");
            }})
            .name("image")
            .build());
        }})
        .build();
        
        Config config = new Config("http","localhost:8080");
        WeaviateClient client = new WeaviateClient(config);

        Result <Boolean> dbResult = client.schema().classCreator().withClass(clazz).run();
        if(dbResult.hasErrors()){
            System.out.println("error occured");
            return;
        }
        System.out.println("db created successfully");
    }
}
