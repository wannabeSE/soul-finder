package com.example.soulfinder.soulfinderbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Model.User;
import com.example.soulfinder.soulfinderbackend.Repository.PostRepos;

@Service
public class PostService {
    
    @Autowired
    PostRepos postRepos;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Post savePostService(String postData, String userId){
        Post post = postRepos.insert(new Post(postData));
        mongoTemplate.update(User.class)
        .matching(Criteria.where("userId").is(userId))
        .apply(new Update().push("postIds").value(postData))
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
}
