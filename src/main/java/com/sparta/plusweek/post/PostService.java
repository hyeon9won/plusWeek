package com.sparta.plusweek.post;

import com.sparta.plusweek.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto);
        post.setUser(user);

        Post saved = postRepository.save(post);

        return new PostResponseDto(saved);
    }

    public PostResponseDto getPostDto(Long postId) {
        Post post = getPost(postId);
        return new PostResponseDto(post);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
}
