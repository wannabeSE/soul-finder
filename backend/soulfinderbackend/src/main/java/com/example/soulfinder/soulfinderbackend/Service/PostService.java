package com.example.soulfinder.soulfinderbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Repository.PostRepos;
import com.example.soulfinder.soulfinderbackend.Wrapper.PostObjectWrapper;

@Service
public class PostService {
    
    @Autowired
    PostRepos postRepos;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public Post savePostService(PostObjectWrapper postObject){

        Post postObj= Post.builder()
            .body(postObject.getPostData())
            .userId(postObject.getUserId())
            .vectorImgIds(postObject.getVecImgIds())
            .build();

        Post post = postRepos.insert(postObj);
        String postId = post.getPostId();
        
        mongoTemplate
            .update(User.class)
            .matching(Criteria.where("userId").is(postObject.getUserId()))
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
            .addCriteria(Criteria.where("userId").is(userId)),
             Post.class);
    }
}
