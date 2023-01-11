package io.k2c1.hereyougo.domain;

import java.time.LocalDateTime;

public class Appointment
{
    private Post post;
    private Member supplier; // 공급자
    private Member demand; // 수요자
    private LocalDateTime timestamp;
    private Address address;
}
