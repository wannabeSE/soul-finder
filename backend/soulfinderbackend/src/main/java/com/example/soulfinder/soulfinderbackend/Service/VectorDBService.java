package com.example.soulfinder.soulfinderbackend.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.VectorImage;
import com.example.soulfinder.soulfinderbackend.Model.WeaviateSchema;
import com.example.soulfinder.soulfinderbackend.Repository.VectorImageRepo;
import com.google.gson.GsonBuilder;

import io.weaviate.client.WeaviateClient;
import io.weaviate.client.base.Result;
import io.weaviate.client.v1.data.model.WeaviateObject;
import io.weaviate.client.v1.graphql.model.GraphQLResponse;
import io.weaviate.client.v1.graphql.query.argument.NearImageArgument;
import io.weaviate.client.v1.graphql.query.fields.Field;
import io.weaviate.client.v1.schema.model.Schema;
import io.weaviate.client.v1.schema.model.WeaviateClass;

@Service
public class VectorDBService {
    
    @Autowired
    private VectorImageRepo vectorImageRepo;
    private WeaviateClient client = WeaviateSchema.retConfig();
    private String createdDBClassName = "Test";

    public void schemaClassBuilder(){
        
        Map<String, Object> img2vec = new HashMap<>();
        HashMap <String, Object> img2vecNeural = new HashMap<>();
        ArrayList <String> imageFields = new ArrayList<>();
        imageFields.add("image");
        img2vecNeural.put("imageFields", imageFields);
        img2vec.put("img2vec-neural", img2vecNeural);
        WeaviateClass clazz = WeaviateClass.builder()
        .className(createdDBClassName)
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
        

        Result <Boolean> dbResult = client.schema().classCreator().withClass(clazz).run();
        if(dbResult.hasErrors()){
            System.out.println("error occurred");
            return;
        }
        System.out.println("db created successfully");
    }

    public String dbClassStatus(){
        Result <Schema> dbResult = client.schema().getter().run();
        if(dbResult.hasErrors()){

            return "Error Occurred";
        }
        String jsonRes = new GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(dbResult.getResult());

        return jsonRes;
    }
    public String fileEncoder(MultipartFile fl) throws IOException{
        
        byte [] fileContent = fl.getBytes();

        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return encodedString;
    }
    
    public String vectorDbImageUploader(MultipartFile fl) throws IOException{
        
        String uniqueID = UUID.randomUUID().toString();
        System.out.println(uniqueID);
        Map<String, Object> dataSchema = new HashMap<>();
        String encodedString = fileEncoder(fl);
        VectorImage vectorImage = new VectorImage(uniqueID, encodedString);

        setVectorImgToMongoService(vectorImage);
        dataSchema.put("image", encodedString);
        Result<WeaviateObject> result = client.data().creator()
        .withClassName(createdDBClassName)
        .withProperties(dataSchema)
        .withID(uniqueID)
        .run();

        if(result.hasErrors()){
            System.out.println("file upload failed");
            return "failed";
        }System.out.println("Successfully uploaded");
        return "Ok";
    }

    public void setVectorImgToMongoService(VectorImage vectorImage){
        vectorImageRepo.save(vectorImage);
    }
    public List<VectorImage> getAllVectorImgFromMongoService(){
        List<VectorImage> images = vectorImageRepo.findAll();
        return images;
    }
    public Object dbItemCounter(){

        Field meta = Field.builder()
            .name("meta")
            .fields(new Field[]{
                Field.builder().name("count").build()
            }).build();
        Result<GraphQLResponse> result = client.graphQL().aggregate()
            .withClassName(createdDBClassName)
            .withFields(meta)
            .run();
        if(result.hasErrors()){
            return result.getError();
        }
        return result.getResult();
    }


    public void imageSearchVectorDB(MultipartFile file){
        String encodedString = "";
        try {
            encodedString = fileEncoder(file);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        NearImageArgument test = client.graphQL().arguments().nearImageArgBuilder()
        .image(encodedString)
        .build();
        Field image = Field.builder().name("image").build();
        Result<GraphQLResponse> result = client.graphQL().get()
            .withClassName(createdDBClassName)
            .withFields(image)
            .withNearImage(test)
            .withLimit(2)
            .run();
        
        if (result.hasErrors()) {
            System.out.println(result.getError());
            return;
        }
        
        var responseImage = ((Map)((ArrayList)((Map)((Map)result
            .getResult()
            .getData())
            .get("Get"))
            .get("Test"))
            .get(0))
            .get("image");
        var responseImage2 = ((Map)((ArrayList)((Map)((Map)result
            .getResult()
            .getData())
            .get("Get"))
            .get("Test"))
            .get(1))
            .get("image");

        try {
            imageConverter(responseImage.toString(), "result_1");
            imageConverter(responseImage2.toString(), "result_2");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    public void deleteVectorDBClass(String className){
        Result<?> res= client.schema().classDeleter().withClassName(className).run();
        if(res.hasErrors()){
            System.out.println("Delete Unsuccessful");
            return;
        }
        System.out.println("Deletion Successful");
    }

    public void getImageByIdService(String id){
        Result<List<WeaviateObject>> result = client.data().objectsGetter()
            .withClassName(createdDBClassName)
            .withID(id)
            .run();

        fileWriter(result.getResult().toString());
    }

    public void deleteImageByIdService(String idToDelete){

        Result<?> res = client.data().deleter()
            .withClassName(createdDBClassName)
            .withID(idToDelete)
            .run();
        if(res.hasErrors()){
            System.out.println("Image Deletion failed");
            return;
        }
        System.out.println("Deletion successful");
    }
    public void fileWriter(String content){
        try {
            FileWriter fileWriter = new FileWriter("/Users/jsamir/Development/soul-finder/backend/res.txt");
            fileWriter.write(content);
            fileWriter.close();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    public void imageConverter(String encodedString, String img) throws IOException{
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        FileUtils.writeByteArrayToFile(new File(img+".jpg"), decodedBytes);
    }

}
