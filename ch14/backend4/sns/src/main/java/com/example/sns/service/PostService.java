package com.example.sns.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sns.domain.PostDto;
import com.example.sns.entity.Post;
import com.example.sns.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

 // 게시물 전체 조회
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getId(), // Extract userId
                        post.getUser().getUsername(), // Extract username
                        post.getContent(),
                        post.getMediaFile(),
                        post.getMediaType().toString(),
                        post.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    // 특정 사용자 게시물 조회
    public List<PostDto> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId).stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getId(),
                        post.getUser().getUsername(),
                        post.getContent(),
                        post.getMediaFile(),
                        post.getMediaType().toString(),
                        post.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    // 게시물 ID로 조회
    public Optional<PostDto> getPostById(Long id) {
        return postRepository.findById(id)
                .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getId(),
                        post.getUser().getUsername(),
                        post.getContent(),
                        post.getMediaFile(),
                        post.getMediaType().toString(),
                        post.getCreatedAt()
                ));
    }

    // 게시물 생성
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // 게시물 수정
    @Transactional
    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id).map(post -> {
            post.setContent(updatedPost.getContent());
            post.setMediaFile(updatedPost.getMediaFile());
            post.setMediaType(updatedPost.getMediaType());
            return postRepository.save(post);
        }).orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
    }

    // 게시물 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}