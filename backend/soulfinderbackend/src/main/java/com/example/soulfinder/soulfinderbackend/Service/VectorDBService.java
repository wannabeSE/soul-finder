package com.example.soulfinder.soulfinderbackend.Service;



import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.WeaviateSchema;

import io.weaviate.client.WeaviateClient;
import io.weaviate.client.base.Result;
import io.weaviate.client.v1.data.model.WeaviateObject;
import io.weaviate.client.v1.graphql.model.GraphQLResponse;
import io.weaviate.client.v1.graphql.query.fields.Field;

@Service
public class VectorDBService {
    
 
    
    public static String fileEncoder(MultipartFile fl) throws IOException{
        ;
        byte [] fileContent = fl.getBytes();

        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return encodedString;
    }
    
    public static String vectorDbImageUploader(MultipartFile fl) throws IOException{
        WeaviateClient client = WeaviateSchema.retConfig();
        Map<String, Object> dataSchema = new HashMap<>();
        String encodedString = fileEncoder(fl);
        dataSchema.put("image", encodedString);
        Result<WeaviateObject> result = client.data().creator()
        .withClassName("Test")
        .withProperties(dataSchema)
        .run();

        if(result.hasErrors()){
            System.out.println("file upload failed");
            return "failed";
        }System.out.println("Successfully uploaded");
        return "Ok";
    }

    public static Object dbItemCounter(){
        WeaviateClient client = WeaviateSchema.retConfig();
        Field meta = Field.builder()
            .name("meta")
            .fields(new Field[]{
                Field.builder().name("count").build()
            }).build();
        Result<GraphQLResponse> result = client.graphQL().aggregate()
            .withClassName("Test")
            .withFields(meta)
            .run();
        if(result.hasErrors()){
            return result.getError();
        }
        return result.getResult();
    }
}
