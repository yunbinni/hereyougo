package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.AppointmentForm;
import io.k2c1.hereyougo.service.AppointmentService;
import io.k2c1.hereyougo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PostService postService;

    @ResponseBody
    @PostMapping("/new")
    public String makeAppointment(AppointmentForm appointmentForm, Model model) {
        String message = "";

        Post post = postService.getPost(appointmentForm.getPostId()).get();
        int postQuantity = post.getQuantity();

        try {
            if (postQuantity < 0) {
                message = "현재 예약이 안됩니다";
                throw new IllegalStateException("현재 예약이 안됩니다");
            } else {
                appointmentService.makeAppointment(appointmentForm);
                message = "예약이 완료되었습니다";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        model.addAttribute("message", message);
        return message;
    }

    @ResponseBody
    @PutMapping("/{id}/status/cancel")
    public void cancelAppointmentStatus(@PathVariable("id") Long appointmentId){
        appointmentService.cancelAppointment(appointmentId);
    }

    @ResponseBody
    @PutMapping("/{id}/status/complete")
    public void completeAppointment(@PathVariable("id") Long appointmentId){
        appointmentService.completeAppointment(appointmentId);
    }

    @GetMapping("/list")
    public String getAppointments(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
             @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable, Model model){
        int displayCount = 10;

        Long memberId = loginMember.getId();
        Page<Appointment> appointments = appointmentService.getAppointments(memberId, pageable);

        int startPage = Math.max(1,appointments.getPageable().getPageNumber() -4);
        int endPage = Math.min(appointments.getTotalPages(),appointments.getPageable().getPageNumber() + 4);

        if(appointments.getTotalElements()<= displayCount){
            endPage = 1;
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("member", loginMember);
        model.addAttribute("member", loginMember);
        model.addAttribute("appointments", appointments);
        return "appointment/AppointmentList";
    }


}
