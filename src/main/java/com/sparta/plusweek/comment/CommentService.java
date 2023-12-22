package com.sparta.plusweek.comment;

import com.sparta.plusweek.post.Post;
import com.sparta.plusweek.post.PostService;
import com.sparta.plusweek.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostService postService;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user) {
        Post post = postService.getPost(commentRequestDto.getPostId());

        Comment comment = new Comment(commentRequestDto);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
