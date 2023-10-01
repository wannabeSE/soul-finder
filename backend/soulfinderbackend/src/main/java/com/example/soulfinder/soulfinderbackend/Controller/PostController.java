package com.example.soulfinder.soulfinderbackend.Controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.soulfinder.soulfinderbackend.Model.Post;
import com.example.soulfinder.soulfinderbackend.Service.PostService;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/post/")
public class PostController {
    
    @Autowired
    PostService postService;

    @GetMapping("/get-all-posts")
    public List<Post> getPost(){
        List<Post> posts = postService.getAllPostService();
        return posts;
    }

    @PostMapping("/save-post") 
    public ResponseEntity<?> postToDb(@RequestParam("image") MultipartFile[] files ,Post postObject){
        return ResponseEntity.status(HttpStatus.OK)
            .body(postService
            .savePostService(postObject, files)
        );
    }
    @GetMapping("/get-post-by-id/{postId}")
    public ResponseEntity<Object> getSinglePost(@PathVariable String postId){
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService.getPostByIdService(postId));
    }

    @GetMapping("/get-user-posts/{userId}")
    public ResponseEntity<List<Post>> getPostByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService.getPostByUserIdService(userId));
    }

    @GetMapping("/get-lost-posts/{type}")
    public ResponseEntity<List<Post>> getAllLostPosts(@PathVariable String type){
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService.getAllLostPostService(type));
    }

    @GetMapping("/get-found-posts/{type}")
    public ResponseEntity<List<Post>> getAllFoundPosts(@PathVariable String type){
        return ResponseEntity.status(HttpStatus.OK)
        .body(postService.getAllFoundPostService(type));
    }

    @PostMapping("/update-post-type")
    public ResponseEntity<Object> updatePostType(@RequestBody Map<String, String> payload){
        return ResponseEntity.status(HttpStatus.OK)
            .body(postService.updatePostTypeService(payload));
    }

    @GetMapping("/get-post-images/{vecId}")
    public ResponseEntity<Object> getPostImages(@PathVariable String vecId){
        return ResponseEntity.status(HttpStatus.OK)
            .body(postService.getPostImageService(vecId));
    }
}
