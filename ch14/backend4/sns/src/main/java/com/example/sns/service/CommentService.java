package com.example.sns.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sns.domain.CommentDto;
import com.example.sns.entity.Comment;
import com.example.sns.entity.Post;
import com.example.sns.entity.User;
import com.example.sns.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    // 특정 게시물의 댓글 조회
    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 댓글 생성
    @Transactional
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        Post post = new Post();
        User user = new User();
        post.setId(commentDto.getPostId());
        user.setId(commentDto.getUserId());
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(commentDto.getContent());

        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    // 댓글 수정
    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다. ID: " + id));

        comment.setContent(commentDto.getContent()); // 내용 업데이트
        Comment updatedComment = commentRepository.save(comment);

        return convertToDto(updatedComment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // Entity -> DTO 변환
    private CommentDto convertToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setPostId(comment.getPost().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setUsername(comment.getUser().getUsername());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}