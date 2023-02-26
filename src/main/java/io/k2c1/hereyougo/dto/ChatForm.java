package io.k2c1.hereyougo.dto;

import lombok.Data;

@Data
public class ChatForm {
    private Long chatRoomId;
    private String sender;
    private String message;
}
