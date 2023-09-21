package com.example.soulfinder.soulfinderbackend.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Model.PostObject;

import com.example.soulfinder.soulfinderbackend.Service.PostService;

@CrossOrigin
@RestController
@RequestMapping("api/posts/")
public class PostController {
    
    @Autowired
    PostService postService;

    @GetMapping("/")
    public String hello(){
        return "server is up 🔥";
    }

    @GetMapping("/get-posts")
    public List<Post> getPost(){
        List<Post> posts = postService.getAllPostService();
        return posts;
    }

    @PostMapping("/save-post")
    public ResponseEntity<?> postToDb(@RequestBody PostObject postObject){
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService
        .savePostService(
            postObject
            )
        );
    }
    @GetMapping("/get-posts/{postId}")
    public ResponseEntity<Object> getSinglePost(@PathVariable String postId){
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService.getPostByIdService(postId));
    }
}
