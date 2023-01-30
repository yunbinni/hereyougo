package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.AppointmentRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AppointmentServiceTest
{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void before()
    {
        // 공급자 수요자 생성 후 리포에 저장
        Member supplier = new Member(0L, "supplier@example.com", "supplier1234", "supplier", "CAFE");
        Member demand = new Member(1L,"demand@example.com", "demand1234", "demand", "CAFE");
        memberRepository.save(supplier);
        memberRepository.save(demand);

        // supplier가 게시글 작성 후 리포에 저장
//        Post post = new Post(0L, supplier, "제목", "내용", 10, 20, 3, 0, "서울 마포구 양화로23길 20 1층", LocalDateTime.now());
//        postRepository.save(post);
    }

    @AfterEach
    void after()
    {
        // clearAll
        memberRepository.deleteAll();
        postRepository.deleteAll();
        appointmentRepository.deleteAll();
    }

    @Test
    void 새_약속_생성()
    {
        // given
        Member supplier = memberRepository.findByEmail("supplier@example.com");
        Member demand = memberRepository.findByEmail("demand@example.com");
        postRepository.findByWriter_Id(supplier.getId());

        // when
        Appointment appointment = new Appointment(0L, demand, LocalDateTime.now());
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // then
        assertThat(appointmentRepository.count()).isEqualTo(1L);
//        assertThat(savedAppointment.getSupplier()).usingRecursiveComparison().isEqualTo(supplier);
        assertThat(savedAppointment.getWanted()).usingRecursiveComparison().isEqualTo(demand);
    }

    @Test
    void 약속조회()
    {
        // given
        Member supplier = memberRepository.findByEmail("supplier@example.com");
        Member demand = memberRepository.findByEmail("demand@example.com");
        appointmentRepository.save(new Appointment(0L, demand, LocalDateTime.now()));

        // when
//        Appointment suppliersAppointment = appointmentRepository.findBySupplier_Id(supplier.getId());
//        Appointment demandsAppointment = appointmentRepository.findByDemand_Id(demand.getId());

        // then
//        assertThat(suppliersAppointment).isEqualTo(demandsAppointment);
    }

    @Test
    void 약속_수정()
    {
        // given
        Member supplier = memberRepository.findByEmail("supplier@example.com");
        Member demand = memberRepository.findByEmail("demand@example.com");
        Appointment beforeAppointment = new Appointment(0L, demand, LocalDateTime.of(2010, 1, 1, 1, 0, 0));
        appointmentRepository.save(beforeAppointment);

        // when
        Appointment findAppointment = appointmentRepository.findById(0L).get();
        Appointment afterAppointment = new Appointment(0L, demand, LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        appointmentRepository.deleteById(findAppointment.getId());
        appointmentRepository.save(afterAppointment);

        // then
//        Appointment suppliersAppointment = appointmentRepository.findBySupplier_Id(supplier.getId());
//        Appointment demandsAppointment = appointmentRepository.findByDemand_Id(demand.getId());

//        assertThat(suppliersAppointment).usingRecursiveComparison().isEqualTo(demandsAppointment);
    }

    @Test
    void 약속_취소()
    {
        // given
        Member supplier = memberRepository.findByEmail("supplier@example.com");
        Member demand = memberRepository.findByEmail("demand@example.com");
        Appointment savedAppointment = appointmentRepository.save(new Appointment(0L, demand, LocalDateTime.now()));

        // when
        appointmentRepository.deleteById(savedAppointment.getId());

        // then
        assertThat(appointmentRepository.count()).isEqualTo(0L);
//        assertThatThrownBy(() -> appointmentRepository.findById(savedAppointment.getId()))
//                .isInstanceOf(NotFoundException.class);
//        assertThatThrownBy(() -> appointmentRepository.findBySupplier_Id(supplier.getId()))
//                .isInstanceOf(NotFoundException.class);
//        assertThatThrownBy(() -> appointmentRepository.findByDemand_Id(demand.getId()))
//                .isInstanceOf(NotFoundException.class);
    }
}