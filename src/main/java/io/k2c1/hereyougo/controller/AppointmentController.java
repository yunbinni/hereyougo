package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.AppointmentForm;
import io.k2c1.hereyougo.service.AppointmentService;
import io.k2c1.hereyougo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
