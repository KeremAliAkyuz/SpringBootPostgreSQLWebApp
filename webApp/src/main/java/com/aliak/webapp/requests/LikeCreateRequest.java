package com.aliak.webapp.requests;

import com.aliak.webapp.entities.Post;
import com.aliak.webapp.entities.User;
import lombok.Data;

@Data
public class LikeCreateRequest {
    Long id;
    Long postId;
    Long userId;
}
