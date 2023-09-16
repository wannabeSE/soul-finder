package com.example.soulfinder.soulfinderbackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Repository.PostRepos;

@Service
public class PostService {
    
    @Autowired
    PostRepos postRepos;

    public Post savePostService(Post postData){
        Post res = postRepos.save(postData);
        return res;
    }

    public List<Post> getAllPostService(){
        List<Post> posts = postRepos.findAll();
        return posts;

    }
}
