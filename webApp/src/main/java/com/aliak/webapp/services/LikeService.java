package com.aliak.webapp.services;

import com.aliak.webapp.entities.Like;
import com.aliak.webapp.entities.Post;
import com.aliak.webapp.entities.User;
import com.aliak.webapp.repository.LikeRepository;
import com.aliak.webapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;
    public LikeService(LikeRepository likeRepository,PostService postService,UserService userService){
        this.likeRepository=likeRepository;
        this.userService=userService;
        this.postService=postService;
    }
    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findAllByPostId(postId.get());
        } else
            return null;
    }

    public Like getOneLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest){
        User user = userService.getOneUser(likeCreateRequest.getUserId());
        Post post = postService.getOnePostById(likeCreateRequest.getPostId());
        if(user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }
        else
            return null;
    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
