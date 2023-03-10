package io.k2c1.hereyougo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentForm {
    private Long chatRoomId;
    private Long postId;
    private Long memberId;
    private String dateTime;
    private int appointmentQuantity; // 구매하고자하는 수량
}