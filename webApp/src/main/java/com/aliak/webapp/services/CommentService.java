package com.aliak.webapp.services;

import com.aliak.webapp.entities.Comment;
import com.aliak.webapp.entities.Post;
import com.aliak.webapp.entities.User;
import com.aliak.webapp.repository.CommentRepository;
import com.aliak.webapp.requests.CommentCreateRequest;
import com.aliak.webapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository,UserService userService,PostService postService){
        this.commentRepository=commentRepository;
        this.postService=postService;
        this.userService=userService;
    }

    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if(postId.isPresent()&& userId.isPresent())
            return commentRepository.findAllByPostIdAndUserId(postId.get(),userId.get());
        else if (userId.isPresent())
            return commentRepository.findAllByUserId(userId.get());//.get() ile içerisindeki valueyu alıyoruz
        else if (postId.isPresent())
            return commentRepository.findAllByPostId(postId.get());
        else
            return commentRepository.findAll();

    }

    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUser(commentCreateRequest.getUserId());
        Post post = postService.getOnePostById(commentCreateRequest.getPostId());
        if(user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setPost(post);//databesden getirdiğimiz post
            commentToSave.setUser(user);//databaseden getirdiğimiz user
            commentToSave.setText(commentCreateRequest.getText());
            return commentRepository.save(commentToSave);}
        else
            return null;

    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {

        Optional <Comment> comment = commentRepository.findById(commentId);//commentRepositoryden kontrol et böyle bir coment varmı yok mu
        if(comment.isPresent()){//eğer varsa
            Comment commentToUpdate = new Comment();//comment objesi oluştur
            commentToUpdate.setText(commentUpdateRequest.getText());//oluşturduğumuz objenin textini requestten gelen objenin textiyle değiştir
            return commentRepository.save(commentToUpdate);//repoya kaydet ve kaydettiğin değeri dön
        }
        else
            return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
