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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String getAppointments(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        Long memberId = loginMember.getId();
        List<Appointment> appointments = appointmentService.getAppointments(memberId);
        model.addAttribute("member", loginMember);
        model.addAttribute("member", loginMember);
        model.addAttribute("appointments", appointments);
        return "appointment/AppointmentList";
    }


}
