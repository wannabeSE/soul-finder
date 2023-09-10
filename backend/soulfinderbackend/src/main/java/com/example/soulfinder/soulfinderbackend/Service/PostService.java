package com.example.soulfinder.soulfinderbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Repository.PostRepos;

@Service
public class PostService {
    
    @Autowired
    PostRepos postRepos;

    public Object savePostService(Post postData){
        Object res = postRepos.save(postData);
        return res;
    }

    public Object getAllPostService(){
        Object response = postRepos.findAll();
        return response;

    }
}
