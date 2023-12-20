package com.sparta.plusweek.post;

import com.sparta.plusweek.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> postPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto postResponseDto = postService.createPost(postRequestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(postResponseDto);
    }

}
