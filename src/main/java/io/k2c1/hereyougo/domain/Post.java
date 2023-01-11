package io.k2c1.hereyougo.domain;

import java.time.LocalDateTime;

public class Post
{
    private Long id;
    private Member writer;
    private String title;
    private String content;
    private String size; // 가로 x 세로 x 높이
    private Long views; // 조회수
    private LocalDateTime timestamp; // 작성일/시각
}
