package io.k2c1.hereyougo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class ChatMessage {

    @Id @GeneratedValue
    @Column(name = "chatmessage_id")
    private Long id;

    private String sender;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    ChatRoom chatRoom;

    @Builder
    public ChatMessage(String sender, String message, ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
        this.sendDate = LocalDateTime.now();
    }
}
