package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.SampleDataInit;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.dto.JusoApiForm;
import io.k2c1.hereyougo.dto.LoginForm;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * TODO 나중에 Controller가 비대해지면, 분류합시다!
 */

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private final PostRepository postRepository;

    @GetMapping("/join")
    public String join(@ModelAttribute("joinForm") JoinForm joinForm) {
        // TODO : 회원가입 검증로직도 추가해야함 (Service에서)
//        memberService.join(joinForm);
        return "join";
    }

    @RequestMapping("/jusoPopup")
    public String jusoPopup(Model model, HttpServletRequest request) {
        JusoApiForm jusoApiForm = new JusoApiForm();
        jusoApiForm.setInputYn(request.getParameter("inputYn"));
        jusoApiForm.setRoadFullAddr(request.getParameter("roadFullAddr"));
        jusoApiForm.setRoadAddrPart1(request.getParameter("roadAddrPart1"));
        jusoApiForm.setRoadAddrPart2(request.getParameter("roadAddrPart2"));
        jusoApiForm.setEngAddr(request.getParameter("engAddr"));
        jusoApiForm.setJibunAddr(request.getParameter("jibunAddr"));
        jusoApiForm.setZipNo(request.getParameter("zipNo"));
        jusoApiForm.setAddrDetail(request.getParameter("addrDetail"));
        jusoApiForm.setAdmCd(request.getParameter("admCd"));
        jusoApiForm.setRnMgtSn(request.getParameter("rnMgtSn"));
        jusoApiForm.setBdMgtSn(request.getParameter("bdMgtSn"));
        jusoApiForm.setDetBdNmList(request.getParameter("detBdNmList"));
        jusoApiForm.setBdNm(request.getParameter("bdNm"));
        jusoApiForm.setBdKdcd(request.getParameter("bdKdcd"));
        jusoApiForm.setSiNm(request.getParameter("siNm"));
        jusoApiForm.setSggNm(request.getParameter("sggNm"));
        jusoApiForm.setEmdNm(request.getParameter("emdNm"));
        jusoApiForm.setLiNm(request.getParameter("liNm"));
        jusoApiForm.setRn(request.getParameter("rn"));
        jusoApiForm.setUdrtYn(request.getParameter("udrtYn"));
        jusoApiForm.setBuldMnnm(request.getParameter("buldMnnm"));
        jusoApiForm.setBuldSlno(request.getParameter("buldSlno"));
        jusoApiForm.setMtYn(request.getParameter("mtYn"));
        jusoApiForm.setLnbrMnnm(request.getParameter("lnbrMnnm"));
        jusoApiForm.setLnbrSlno(request.getParameter("lnbrSlno"));
        jusoApiForm.setEmdNo(request.getParameter("emdNo"));

        model.addAttribute("jusoApiForm", jusoApiForm);
        return "jusoPopup";
    }

    @GetMapping("/mapExample")
    public String mapExample() {
        return "mapExample";
    }
}
