package com.example.soulfinder.soulfinderbackend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Model.VectorImage;
import com.example.soulfinder.soulfinderbackend.Repository.PostRepos;


@Service
public class PostService {
    
    @Autowired
    private PostRepos postRepos;
    @Autowired
    private VectorDBService vectorDBService;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public Post savePostService(MultipartFile[] files, Post postObject){

        Executor executor = Executors.newFixedThreadPool(3);
        CompletableFuture<String> imageUpload = new CompletableFuture<>();
        List<CompletableFuture<String>> urlList = new ArrayList<>();

        //* Multi-threading (Async)*/
        for (MultipartFile file : files) {
            

             imageUpload = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("Executed by : " + Thread.currentThread().getName());
                    return vectorDBService.postImageUploadService(file, postObject.getUserId());
                
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }

            }, executor);
            
            urlList.add(imageUpload);
        }

        //* Converting CompletableFuture List to List */
        List<String> resultList = urlList.stream()
                .map(CompletableFuture::join)  
                .collect(Collectors.toList());

        try {
            postObject.setImageUrls(resultList);
        } catch (Exception e) {
            throw new RuntimeException("Image url setting failed");
        }
        
        //? Saving post data in MongoDB
        Post post = postRepos.insert(postObject);
        String postId = post.getPostId();
        
        mongoTemplate
            .update(User.class)
            .matching(Criteria
            .where("firebaseUID")
            .is(postObject.getUserId())) //?userId changed to firebaseUID
            .apply(new Update().push("postIds").value(postId))
            .first();
        
        return post;
    }
    
    //! Deprecated single thread method
    // public Post savePostService(Post postObject, MultipartFile[] files){

    //     List<String> imgUrlList = new ArrayList<>();
    //     try {
    //         imgUrlList = vectorDBService.vectorDbImageUploader(files, postObject.getUserId());
    //     } catch (Exception e) {
    //         System.out.println(e.getLocalizedMessage());
    //     }

    //     postObject.setImageUrls(imgUrlList);
    //     Post post = postRepos.insert(postObject);

    //     String postId = post.getPostId();
        
    //     mongoTemplate
    //         .update(User.class)
    //         .matching(Criteria
    //         .where("firebaseUID")
    //         .is(postObject.getUserId())) //?userId changed to firebaseUID
    //         .apply(new Update().push("postIds").value(postId))
    //         .first();
        
    //     return post;
    // }

    public List<Post> getAllPostService(){
        List<Post> posts = postRepos.findAll();
        return posts;

    }

    public Optional<Post> getPostByIdService(String postId){
        Optional<Post> post = postRepos.findById(postId);
        return post;
    }

    public List<Post> getPostByUserIdService(String userId){
        return mongoTemplate
            .find(new Query()
            .addCriteria(Criteria.where("firebaseUID").is(userId)), //?userId changed to firebaseUID
             Post.class);
    }

    public List<Post> getAllLostPostService(String lostPost){
        return mongoTemplate
            .find(new Query()
            .addCriteria(Criteria.where("postType").is(lostPost)),
            Post.class);
    }
    
    public List<Post> getAllFoundPostService(String foundPost){
        return mongoTemplate
            .find(new Query()
            .addCriteria(Criteria.where("postType").is(foundPost)),
            Post.class);
    }

    public Object updatePostTypeService(Map<String,String> data){
        return mongoTemplate
            .update(Post.class)
            .matching(Criteria.where("postId").is(data.get("postId")))
            .apply(Update.update("postType", data.get("type")))
            .first();
    }

    public Object getPostImageService(String vecId){
        return mongoTemplate
            .find(new Query()
            .addCriteria(Criteria.where("vectorDbId").is(vecId)), 
            VectorImage.class);
    }
}
