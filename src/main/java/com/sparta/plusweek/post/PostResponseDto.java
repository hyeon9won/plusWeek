package com.sparta.plusweek.post;

import com.sparta.plusweek.CommonResponseDto;
import com.sparta.plusweek.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class PostResponseDto extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private UserResponseDto user;
    private LocalDateTime createDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = new UserResponseDto(post.getUser());
        this.createDate = post.getCreateDate();
    }

    public PostResponseDto(String message, Integer statusCode) {
        super(message, statusCode);
    }
}
