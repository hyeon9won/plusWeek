package com.sparta.plusweek.post;

import com.sparta.plusweek.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 2, max = 500, message = "제목은 2 ~ 500자 이내로 입력해야 합니다.")
    private String title;

    @Column
    @Size(min = 2, max = 5000, message = "내용은 2 ~ 5,000자 이내로 입력해야 합니다.")
    private String content;

    @Column
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.createDate = LocalDateTime.now();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) { this.title = title; }

    public void setContent(String content) { this.content = content; }
}
