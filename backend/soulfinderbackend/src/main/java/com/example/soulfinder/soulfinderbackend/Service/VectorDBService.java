package com.example.soulfinder.soulfinderbackend.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.VectorImage;
import com.example.soulfinder.soulfinderbackend.Model.WeaviateSchema;
import com.example.soulfinder.soulfinderbackend.Repository.VectorImageRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import io.weaviate.client.WeaviateClient;
import io.weaviate.client.base.Result;
import io.weaviate.client.v1.data.model.WeaviateObject;
import io.weaviate.client.v1.graphql.model.GraphQLResponse;
import io.weaviate.client.v1.graphql.query.argument.NearImageArgument;
import io.weaviate.client.v1.graphql.query.fields.Field;
import io.weaviate.client.v1.schema.model.Property;
import io.weaviate.client.v1.schema.model.Schema;
import io.weaviate.client.v1.schema.model.WeaviateClass;


@Service
public class VectorDBService {
    
    @Autowired
    private VectorImageRepo vectorImageRepo;
    @Autowired
    private MongoDBServices mongoDBServices;

    private WeaviateClient client = WeaviateSchema.retConfig();

    private String createdDBClassName = "Test";

    @Autowired
    private CloudinaryImageUploadService cloudinaryImageUploadService;

    private int vectorDbReturnResponseLimit = 4;

    public String schemaClassBuilder(){
        
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
            add(Property.builder()
            .dataType(new ArrayList<>(){{
                add("blob");
            }})
            .name("image")
            .build());
        }})
        .build();
        

        Result <Boolean> dbResult = client.schema().classCreator().withClass(clazz).run();
        if(dbResult.hasErrors()){
            return "failed to create db schema";
        }
        return "db created successfully";
    }

    public String dbClassStatus(){
        Result<Schema> dbResult = client.schema().getter().run();
        if(dbResult.hasErrors()){

            return "Error Occurred";
        }
        String jsonRes = jsonResponseCreator(dbResult.getResult());

        return jsonRes;
    }
    public String fileEncoder(MultipartFile fl) throws IOException{
        
        byte [] fileContent = fl.getBytes();

        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return encodedString;
    }
    
    public List<String> vectorDbImageUploader(MultipartFile[] files, String userId) throws IOException{ 
        
        List<String> imageUrlList = new ArrayList<>();
        Map<String, Object> dataSchema = new HashMap<>();

        for (MultipartFile file : files){

            String uniqueID = UUID.randomUUID().toString();

            System.out.println(uniqueID); //* for debugging purpose *//

            //vectorImgIdList.add(uniqueID);

            String encodedString = fileEncoder(file);

            //? Storing image in cloud storage and getting image URL
            String imgUrl = cloudinaryImageUploadService.upload(file); 
            imageUrlList.add(imgUrl);
            VectorImage vectorImage = VectorImage.builder()
                .vectorDbId(uniqueID)
                .imgUrl(imgUrl)
                .userId(userId)
                .build();
                
            //? Storing image meta data in MongDB
            mongoDBServices.setVectorImgToMongoService(vectorImage);

            //? Storing image meta data in Weaviate VectorDB
            dataSchema.put("image", encodedString);
            Result<WeaviateObject> result = client
                .data()
                .creator()
                .withClassName(createdDBClassName)
                .withProperties(dataSchema)
                .withID(uniqueID)
                .run();

            if(result.hasErrors()){
                System.out.println("Error Occurred");
            }else{
                System.out.println("Post Image upload success");
            }
        }
        return imageUrlList;
        
    }

    
    public List<VectorImage> getAllVectorImgFromMongoService(){
        List<VectorImage> imageList = vectorImageRepo.findAll();
        return imageList;
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


    public Object imageSearchVectorDB(MultipartFile file){
        String encodedString = "";
        List<Object> matchList = new ArrayList<>();
        try {
            encodedString = fileEncoder(file);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        NearImageArgument test = client
            .graphQL()
            .arguments()
            .nearImageArgBuilder()
            .image(encodedString)
            .build();

        Field image = Field.builder().name("image").build();

        Field _additional = Field.builder()
            .name("_additional")
            .fields(new Field[]{ 
            Field.builder().name("distance").build(),
            Field.builder().name("certainty").build(),
            Field.builder().name("id").build() 
            }).build();

        Result<GraphQLResponse> result = client.graphQL().get()
            .withClassName(createdDBClassName)
            .withFields(image, _additional)
            .withNearImage(test)
            .withLimit(vectorDbReturnResponseLimit)
            .run();
        
        if (result.hasErrors()) {
            System.out.println(result.getError());
            return result.getError();
        }
        String jsonRes = jsonResponseCreator(result.getResult().getData());

        fileWriter(jsonRes); //* to check json response */

        
        for(int i = 0; i < vectorDbReturnResponseLimit; i++){
            
            matchList.add(idRetriever(jsonRes, i));
        }

        return matchList;
        
    }
    public String deleteVectorDBClass(String className){
        Result<?> res= client.schema().classDeleter().withClassName(className).run();
        if(res.hasErrors()){

            return "Delete Unsuccessful";
        }
        return "Deletion Successful";
    }

    public String getImageByIdService(String id){

        Result<List<WeaviateObject>> result = client.data().objectsGetter()
            .withClassName(createdDBClassName)
            .withID(id)
            .run();

        String jsonRes = jsonResponseCreator(result.getResult());
        fileWriter(jsonRes);
        return jsonRes;
        
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
    //*To check json response */
    public void fileWriter(String content){
        try {
            FileWriter fileWriter = new FileWriter("res.txt");
            fileWriter.write(content);
            fileWriter.close();

        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public Object idRetriever(String objectToDecode, int index){
        
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(objectToDecode);
            JsonNode id = root
                .path("Get")
                .path("Test")
                .get(index)
                .path("_additional")
                .path("id");
            String idString = id.asText();
            return mongoDBServices.returnImageMatches(idString);
 
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String jsonResponseCreator(Object result){
        String jsonRes = new GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(result);
        return jsonRes;
    }
    //? This method is  DEPRECATED for now
    // public String imageRetriever(String objectToDecode, int index, String imgName){ 
    //     ObjectMapper mapper = new ObjectMapper();
    //     try {
    //         JsonNode rootNode = mapper.readTree(objectToDecode);
    //         JsonNode image = rootNode
    //             .path("Get")
    //             .path("Test")
    //             .get(index)
    //             .path("image");
    //         String imageString = image.asText();
    //         imageConverter(imageString, imgName);
    //         return imageString;
    //     } catch (Exception e) {
    //         return e.getLocalizedMessage();
    //     }
    // }
    //? This method is not needed for now
    // public void imageConverter(String encodedString, String img) throws IOException{
    //     byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    //     FileUtils.writeByteArrayToFile(new File(img+".jpg"), decodedBytes);
    // }

}