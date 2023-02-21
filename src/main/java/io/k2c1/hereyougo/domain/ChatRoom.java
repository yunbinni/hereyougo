package io.k2c1.hereyougo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private Long writerId;

//  최근 메시지 
    private String resentMessage;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public ChatRoom(Post post, Member member, Long writerId){
        this.post = post;
        this.member = member;
        this.writerId = writerId;
    }

    public void updateRecentMessage(String message){
        this.resentMessage = message;
    }
}
