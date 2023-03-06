package io.k2c1.hereyougo.domain;

import io.k2c1.hereyougo.constant.Progress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    /**
     * TODO JoinColumn의 name에 관해서 자세한 구글링을 해봐야 할듯해요! 😉
     */
    @ManyToOne
    @JoinColumn(name = "wanted_id")
    private Member wanted;

//    @OneToOne
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * 약속 목록에서 항목을 클릭할 경우
     * 채팅방으로 이동되게 하기위해 chatroom_id를 선언
     */
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private int appointmentQuantity; // 예약 수량

    private LocalDateTime timestamp; // 약속한 일/시

    @Enumerated(EnumType.STRING)
    private Progress progress;
}
