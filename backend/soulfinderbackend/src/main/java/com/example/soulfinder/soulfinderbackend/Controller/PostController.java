package com.example.soulfinder.soulfinderbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Service.PostService;

@RestController
@RequestMapping("api/posts/")
public class PostController {
    
    @Autowired
    PostService postService;

    @GetMapping("/")
    public String hello(){
        return "server is up";
    }

    @GetMapping("/get-posts")
    public Object getPost(){
        Object posts = postService.getAllPostService();
        return posts;
    }

    @PostMapping("/save-post")
    public Post postToDb(@RequestBody Post post){
        postService.savePostService(post);
        return post;
    }
}
