package com.sparta.plusweek.post;

import com.sparta.plusweek.CommonResponseDto;
import com.sparta.plusweek.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> getPost(@PathVariable Long postId) {
        try {
            PostResponseDto postResponseDto = postService.getPostDto(postId);
            return ResponseEntity.ok().body(postResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

    }
}
