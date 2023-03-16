package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.constant.PostStatus;
import io.k2c1.hereyougo.constant.Progress;
import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.ChatRoom;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.AppointmentForm;
import io.k2c1.hereyougo.repository.AppointmentRepository;
import io.k2c1.hereyougo.repository.ChatRoomRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private MemberRepository memberRepository;

    /**
     * 약속 등록
     */
    public void makeAppointment(AppointmentForm appointmentForm){
        Appointment appointment= new Appointment();

        Long postId = appointmentForm.getPostId();
        Long roomId = appointmentForm.getChatRoomId();
        Long memberId = appointmentForm.getMemberId();

        Post post =  postRepository.findById(postId).get();
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        Member member = memberRepository.findById(memberId).get();
        log.info("멤버 아이디" + memberId);

        int postQuantity = post.getQuantity();
        int reservationQuantity = post.getReservationQuantity(); // 현재 post 예약 수량
        int appointmentQuantity = appointmentForm.getAppointmentQuantity(); // 구매자가 원하는 수량
        int remainQuantity =  postQuantity - reservationQuantity;

        String dateString = appointmentForm.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

//      구매자가 원하는 수량(=약속 수량)과 Post 예약 수량 비교
        if(appointmentQuantity <= remainQuantity ){

            appointment.setPost(post);
            appointment.setChatRoom(chatRoom);
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

    public Page<Appointment> getAppointments(Long memberId, Pageable pageable){
        Member member = memberRepository.findById(memberId).get();
//        Appointment에 chatRoom id 넣어야되는지 고민 => 약속목록에서 제목 클릭 시 채팅방으로 이동되게하려면
//        필요해보임
        return appointmentRepository.getAppointments(member, member, pageable);
    }

    public void cancelAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        Long postId = appointment.getPost().getId();
        Post post = postRepository.findById(postId).get();

        appointment.setProgress(Progress.CANCEL);

        int reservationQuantity = post.getReservationQuantity();
        int cancelQuantity = appointment.getAppointmentQuantity();

        log.info("포스트 수량" + post.getReservationQuantity());
        post.minusReservationQuantity(cancelQuantity);
        log.info("수량 적어진" + post.getReservationQuantity());
    }

    public void completeAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        Long postId = appointment.getPost().getId();
        Post post = postRepository.findById(postId).get();

        appointment.setProgress(Progress.DONE);

        int completeQuantity = appointment.getAppointmentQuantity();

        log.info("포스트 수량" + post.getReservationQuantity());

        post.minusReservationQuantity(completeQuantity);
        post.minusPostQuantity(completeQuantity);
        log.info("수량 적어진" + post.getReservationQuantity());

        if(post.getQuantity() == 0){
            post.setPostStatus(PostStatus.DONATING_DONE);
        }
    }


}
