package com.aliak.webapp.controllers;

import com.aliak.webapp.entities.Post;
import com.aliak.webapp.requests.PostCreateRequest;
import com.aliak.webapp.requests.PostUpdateRequest;
import com.aliak.webapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    //Bir user'ın bütün postlarını get'ler
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional <Long> userId){
        //Optional parametre yani geledebilir gelmeyedebilir gelmediği durumda /postsu çağırır geldiği durumda /posts?userId={userId}
        //bize gelen request içindeki parametreleri pars et ve sağında bulunan değişkenin içersine at. /posts?userId={userId}

        return postService.getAllPosts(userId);
    }
    //Bir postu çağırır
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId,updatePost);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
}
