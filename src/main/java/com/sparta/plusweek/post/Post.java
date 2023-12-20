package com.sparta.plusweek.post;

import com.sparta.plusweek.user.User;
import jakarta.persistence.*;
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
    private String title;

    @Column
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
}
