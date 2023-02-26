package io.k2c1.hereyougo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private Long writerId;

    private String writerNickname;

    private String memberNickname;

    private LocalDateTime resentDate;

//  최근 메시지 
    @Column(columnDefinition = "TEXT")
    private String resentMessage;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public ChatRoom(Post post, Member member, String memberNickname, Long writerId, String writerNickname){
        this.post = post;
        this.member = member;
        this.writerId = writerId;
        this.memberNickname = memberNickname;
        this.writerNickname = writerNickname;
    }

    public void updateRecentMessage(String message){
        this.resentMessage = message;
    }

    public void updateRecentDate(LocalDateTime resentDate){
        this.resentDate = resentDate;
    }
}
