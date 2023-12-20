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

        var saved = postRepository.save(post);

        return new PostResponseDto(saved);
    }

}
