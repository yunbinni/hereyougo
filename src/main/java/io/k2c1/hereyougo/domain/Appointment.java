package io.k2c1.hereyougo.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

public class Appointment
{
    private Post post;
    private Member supplier; // 공급자
    private Member demand; // 수요자
    private LocalDateTime timestamp;
    private Address address;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
