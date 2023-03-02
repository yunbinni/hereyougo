package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.constant.Progress;
import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.AppointmentForm;
import io.k2c1.hereyougo.repository.AppointmentRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Transactional
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    /**
     * 약속 등록
     */
    public void makeAppointment(AppointmentForm appointmentForm){
        Appointment appointment= new Appointment();

        Long postId = appointmentForm.getPostId();

        Post post =  postRepository.findById(postId).get();

        int postQuantity = post.getQuantity();
        int reservationQuantity = post.getReservationQuantity(); // 현재 post 예약 수량
        int appointmentQuantity = appointmentForm.getAppointmentQuantity(); // 구매자가 원하는 수량
        int remainQuantity =  postQuantity - reservationQuantity;

        Long memberId = appointmentForm.getMemberId();
        Member member = memberRepository.findById(memberId).get();
        log.info("멤버 아이디" + memberId);

        String dateString = appointmentForm.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

//      구매자가 원하는 수량(=약속 수량)과 Post 예약 수량 비교
        if(appointmentQuantity <= remainQuantity ){

            appointment.setPost(post);
            appointment.setWanted(member);
            appointment.setProgress(Progress.RESERVING);
            appointment.setTimestamp(dateTime);
            appointment.setAppointmentQuantity(appointmentQuantity);

            appointmentRepository.save(appointment);

            post.plusReservationQuantity(appointmentQuantity);

        }else if(remainQuantity == 0){
            throw new IllegalStateException("현재 예약이 불가능 합니다");
        }else{
            throw new IllegalStateException(remainQuantity+ "개 예약이 가능 합니다");
        }
    }

//    public boolean isExceed(){
//
//    }
}
