package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.dto.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/login")
    public String login(Model model){
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @GetMapping("/join")
    public String join(Model model){
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);
        return "join";
    }

}
