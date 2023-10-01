package com.example.soulfinder.soulfinderbackend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Repository.PostRepos;


@Service
public class PostService {
    
    @Autowired
    private PostRepos postRepos;
    @Autowired
    private VectorDBService vectorDBService;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public Post savePostService(Post postObject, MultipartFile[] files){

        List<String> vectorImgIds = new ArrayList<>();
        try {
            vectorImgIds = vectorDBService.vectorDbImageUploader(files, postObject.getUserId());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        postObject.setVectorImgIds(vectorImgIds);
        Post post = postRepos.insert(postObject);

        String postId = post.getPostId();
        
        mongoTemplate
            .update(User.class)
            .matching(Criteria.where("firebaseUID").is(postObject.getUserId())) //?userId changed to firebaseUID
            .apply(new Update().push("postIds").value(postId))
            .first();
        
        return post;
    }

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
}
