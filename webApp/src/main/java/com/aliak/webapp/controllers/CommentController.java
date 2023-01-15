package com.aliak.webapp.controllers;

import com.aliak.webapp.entities.Comment;
import com.aliak.webapp.requests.CommentCreateRequest;
import com.aliak.webapp.requests.CommentUpdateRequest;
import com.aliak.webapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }
    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId,@RequestParam Optional<Long> userId){
        return commentService.getAllComments(postId,userId);
    }
    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneComment(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneComment(commentId,commentUpdateRequest);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment(commentId);
    }

}
