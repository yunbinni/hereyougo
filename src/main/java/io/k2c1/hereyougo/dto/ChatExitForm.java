package io.k2c1.hereyougo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatExitForm {
    private Long chatRoomId;
    private Long memberId;
    private Long writerId;
}
