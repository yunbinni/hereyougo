package io.k2c1.hereyougo.domain;

import java.time.LocalDateTime;

public class Chat
{
    private Post post; // 게시글
    private Member fromMember; // 송신자
    private Member toMember; // 수신자
    private String message;
    private LocalDateTime timestamp; // 날짜/시각
}
