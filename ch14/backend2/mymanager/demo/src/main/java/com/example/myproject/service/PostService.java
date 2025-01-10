package com.example.myproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.myproject.entity.Post;
import com.example.myproject.exception.ResourceNotFoundException;
import com.example.myproject.repository.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsByBoardId(Long boardId) {
        return postRepository.findByBoardId(boardId);
    }

    // Update post title or content
    public Post updatePost(Long id, Map<String, String> updatedFields) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (updatedFields.containsKey("title")) {
            post.setTitle(updatedFields.get("title"));
        }
        if (updatedFields.containsKey("content")) {
            post.setContent(updatedFields.get("content"));
        }
        return postRepository.save(post);
    }

    // Delete post
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        postRepository.delete(post);
    }
}