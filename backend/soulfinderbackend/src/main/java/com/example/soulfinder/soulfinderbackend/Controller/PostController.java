package com.example.soulfinder.soulfinderbackend.Controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.soulfinder.soulfinderbackend.Service.PostService;

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
    public Object getPost(){
        Object posts = postService.getAllPostService();
        return posts;
    }

    @PostMapping("/save-post")
    public ResponseEntity<?> postToDb(@RequestBody Map<String, String> payload){
        //postService.savePostService(payload.get("postData"),payload.get("userId"));
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService.savePostService(payload.get("postData"),payload.get("userId")));
        //return postService.savePostService(payload.get("postData"),payload.get("userId"));
    }
}
